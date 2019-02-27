package com.github.insanusmokrassar.TelegramBotAPI.utils.extensions

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.InputFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.webhook.SetWebhook
import com.github.insanusmokrassar.TelegramBotAPI.types.MediaGroupIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.Netty
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.serialization.json.Json
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Reverse proxy webhook.
 *
 * @param url URL of webhook WITHOUT including of [port]
 * @param port port which will be listen by bot
 * @param certificate [com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile] or [com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId]
 * which will be used by telegram to send encrypted messages
 * @param scope Scope which will be used for
 */
suspend fun RequestsExecutor.setWebhook(
    url: String,
    port: Int,
    certificate: InputFile,
    scope: CoroutineScope = CoroutineScope(Executors.newFixedThreadPool(4).asCoroutineDispatcher()),
    allowedUpdates: List<String>? = null,
    maxAllowedConnections: Int? = null,
    engineFactory: ApplicationEngineFactory<*, *> = Netty,
    block: UpdateReceiver<Any>
): Job {
    val executeDeferred = executeAsync(
        SetWebhook(
            url,
            certificate,
            maxAllowedConnections,
            allowedUpdates
        )
    )
    val updatesChannel = Channel<Update>(Channel.UNLIMITED)
    val mediaGroupChannel = Channel<Pair<MediaGroupIdentifier, Update>>(Channel.UNLIMITED)
    val mediaGroupAccumulatedChannel = mediaGroupChannel.accumulateByKey(
        1000L,
        scope = scope
    )
    val env = applicationEngineEnvironment {
        module {
            fun Application.main() {
                routing {
                    post("/") {
                        val deserialized = call.receiveText()
                        val update = Json.nonstrict.parse(
                            RawUpdate.serializer(),
                            deserialized
                        )
                        updatesChannel.send(update.asUpdate)
                        call.respond("Ok")
                    }
                }
            }
            main()
        }
        connector {
            host = "0.0.0.0"
            this.port = port
        }
    }
    val engine = embeddedServer(engineFactory, env)

    try {
        executeDeferred.await()
    } catch (e: Exception) {
        env.stop()
        throw e
    }

    return scope.launch {
        launch {
            for (update in updatesChannel) {
                val data = update.data
                when (data) {
                    is MediaGroupMessage -> mediaGroupChannel.send(data.mediaGroupId to update)
                    else -> block(update)
                }
            }
        }
        launch {
            for (mediaGroupUpdate in mediaGroupAccumulatedChannel) {
                block(mediaGroupUpdate.second)
            }
        }
        engine.start(false)
    }.also {
        it.invokeOnCompletion {
            engine.stop(1000L, 0L, TimeUnit.MILLISECONDS)
        }
    }
}

suspend fun RequestsExecutor.setWebhook(
    url: String,
    port: Int,
    certificate: InputFile,
    filter: UpdatesFilter,
    scope: CoroutineScope = CoroutineScope(Executors.newFixedThreadPool(4).asCoroutineDispatcher()),
    maxAllowedConnections: Int? = null,
    engineFactory: ApplicationEngineFactory<*, *> = Netty
): Job = setWebhook(
    url,
    port,
    certificate,
    scope,
    filter.allowedUpdates,
    maxAllowedConnections,
    engineFactory,
    filter.asUpdateReceiver
)