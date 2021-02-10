package dev.inmo.tgbotapi.extensions.utils.updates.retrieving

import dev.inmo.micro_utils.coroutines.*
import dev.inmo.tgbotapi.bot.RequestsExecutor
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.bot.exceptions.RequestException
import dev.inmo.tgbotapi.extensions.utils.updates.convertWithMediaGroupUpdates
import dev.inmo.tgbotapi.extensions.utils.updates.lastUpdateIdentifier
import dev.inmo.tgbotapi.requests.GetUpdates
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.update.*
import dev.inmo.tgbotapi.types.update.MediaGroupUpdates.*
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.updateshandlers.*
import dev.inmo.tgbotapi.utils.*
import io.ktor.client.features.HttpRequestTimeoutException
import io.ktor.utils.io.core.use
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.coroutineContext

fun TelegramBot.startGettingOfUpdatesByLongPolling(
    timeoutSeconds: Seconds = 30,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    exceptionsHandler: (ExceptionHandler<Unit>)? = null,
    allowedUpdates: List<String>? = null,
    updatesReceiver: UpdateReceiver<Update>
): Job = scope.launch {
    var lastUpdateIdentifier: UpdateIdentifier? = null

    while (isActive) {
        safely(
            { e ->
                exceptionsHandler ?.invoke(e)
                if (e is RequestException) {
                    delay(1000L)
                }
            }
        ) {
            val updates = execute(
                GetUpdates(
                    offset = lastUpdateIdentifier?.plus(1),
                    timeout = timeoutSeconds,
                    allowed_updates = allowedUpdates
                )
            ).let { originalUpdates ->
                val converted = originalUpdates.convertWithMediaGroupUpdates()
                /**
                 * Dirty hack for cases when the media group was retrieved not fully:
                 *
                 * We are throw out the last media group and will reretrieve it again in the next get updates
                 * and it will guarantee that it is full
                 */
                if (originalUpdates.size == getUpdatesLimit.last && converted.last() is SentMediaGroupUpdate) {
                    converted - converted.last()
                } else {
                    converted
                }
            }

            safely {
                for (update in updates) {
                    updatesReceiver(update)

                    lastUpdateIdentifier = update.lastUpdateIdentifier()
                }
            }
        }
    }
}

fun TelegramBot.retrieveAccumulatedUpdates(
    avoidInlineQueries: Boolean = false,
    avoidCallbackQueries: Boolean = false,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    exceptionsHandler: (ExceptionHandler<Unit>)? = null,
    allowedUpdates: List<String>? = null,
    updatesReceiver: UpdateReceiver<Update>
): Job = scope.launch {
    safelyWithoutExceptions {
        startGettingOfUpdatesByLongPolling(
            0,
            CoroutineScope(coroutineContext + SupervisorJob()),
            {
                if (it is HttpRequestTimeoutException) {
                    throw CancellationException("Cancel due to absence of new updates")
                } else {
                    exceptionsHandler ?.invoke(it)
                }
            },
            allowedUpdates
        ) {
            when {
                it is InlineQueryUpdate && avoidInlineQueries ||
                it is CallbackQueryUpdate && avoidCallbackQueries -> return@startGettingOfUpdatesByLongPolling
                else -> updatesReceiver(it)
            }
        }.join()
    }
}

/**
 * @return [kotlinx.coroutines.flow.Flow] which will emit updates to the collector while they will be accumulated. Works
 * the same as [retrieveAccumulatedUpdates], but pass [kotlinx.coroutines.flow.FlowCollector.emit] as a callback
 */
fun TelegramBot.createAccumulatedUpdatesRetrieverFlow(
    avoidInlineQueries: Boolean = false,
    avoidCallbackQueries: Boolean = false,
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    allowedUpdates: List<String>? = null
): Flow<Update> = channelFlow {
    val parentContext = kotlin.coroutines.coroutineContext
    channel.apply {
        retrieveAccumulatedUpdates(
            avoidInlineQueries,
            avoidCallbackQueries,
            CoroutineScope(parentContext),
            exceptionsHandler,
            allowedUpdates,
            ::send
        ).join()
        close()
    }
}

fun TelegramBot.retrieveAccumulatedUpdates(
    flowsUpdatesFilter: FlowsUpdatesFilter,
    avoidInlineQueries: Boolean = false,
    avoidCallbackQueries: Boolean = false,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    exceptionsHandler: ExceptionHandler<Unit>? = null
) = flowsUpdatesFilter.run {
    retrieveAccumulatedUpdates(avoidInlineQueries, avoidCallbackQueries, scope, exceptionsHandler, allowedUpdates, asUpdateReceiver)
}

/**
 * Will [startGettingOfUpdatesByLongPolling] using incoming [flowsUpdatesFilter]. It is assumed that you ALREADY CONFIGURE
 * all updates receivers, because this method will trigger getting of updates and.
 */
fun TelegramBot.longPolling(
    flowsUpdatesFilter: FlowsUpdatesFilter,
    timeoutSeconds: Seconds = 30,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    exceptionsHandler: ExceptionHandler<Unit>? = null
): Job = flowsUpdatesFilter.run {
    startGettingOfUpdatesByLongPolling(timeoutSeconds, scope, exceptionsHandler, allowedUpdates, asUpdateReceiver)
}

/**
 * Will enable [longPolling] by creating [FlowsUpdatesFilter] with [flowsUpdatesFilterUpdatesKeeperCount] as an argument
 * and applied [flowUpdatesPreset]. It is assumed that you WILL CONFIGURE all updates receivers in [flowUpdatesPreset],
 * because of after [flowUpdatesPreset] method calling will be triggered getting of updates.
 */
@Suppress("unused")
fun TelegramBot.longPolling(
    timeoutSeconds: Seconds = 30,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    flowsUpdatesFilterUpdatesKeeperCount: Int = 100,
    flowUpdatesPreset: FlowsUpdatesFilter.() -> Unit
): Job = longPolling(FlowsUpdatesFilter(flowsUpdatesFilterUpdatesKeeperCount).apply(flowUpdatesPreset), timeoutSeconds, scope, exceptionsHandler)

/**
 * This method will create a new one [FlowsUpdatesFilter]. This method could be unsafe due to the fact that it will start
 * getting updates IMMEDIATELY. That means that your bot will be able to skip some of them until you will call
 * [kotlinx.coroutines.flow.Flow.collect] on one of [FlowsUpdatesFilter] flows. To avoid it, you can pass
 * [flowUpdatesPreset] lambda - it will be called BEFORE starting updates getting
 */
@FlowPreview
@Deprecated("Will be removed soon", ReplaceWith("longPolling", "dev.inmo.tgbotapi.extensions.utils.updates.retrieving.longPolling"))
@Suppress("unused")
fun RequestsExecutor.startGettingFlowsUpdatesByLongPolling(
    timeoutSeconds: Seconds = 30,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    flowsUpdatesFilterUpdatesKeeperCount: Int = 100,
    flowUpdatesPreset: FlowsUpdatesFilter.() -> Unit = {}
): FlowsUpdatesFilter = FlowsUpdatesFilter(flowsUpdatesFilterUpdatesKeeperCount).apply {
    flowUpdatesPreset()
    startGettingOfUpdatesByLongPolling(timeoutSeconds, scope, exceptionsHandler, allowedUpdates, asUpdateReceiver)
}

fun RequestsExecutor.startGettingOfUpdatesByLongPolling(
    updatesFilter: UpdatesFilter,
    timeoutSeconds: Seconds = 30,
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
): Job = startGettingOfUpdatesByLongPolling(
    timeoutSeconds,
    scope,
    exceptionsHandler,
    updatesFilter.allowedUpdates,
    updatesFilter.asUpdateReceiver
)

@Deprecated("Will be removed soon", ReplaceWith("longPolling", "dev.inmo.tgbotapi.extensions.utils.updates.retrieving.longPolling"))
fun RequestsExecutor.startGettingOfUpdatesByLongPolling(
    messageCallback: UpdateReceiver<MessageUpdate>? = null,
    messageMediaGroupCallback: UpdateReceiver<MessageMediaGroupUpdate>? = null,
    editedMessageCallback: UpdateReceiver<EditMessageUpdate>? = null,
    editedMessageMediaGroupCallback: UpdateReceiver<EditMessageMediaGroupUpdate>? = null,
    channelPostCallback: UpdateReceiver<ChannelPostUpdate>? = null,
    channelPostMediaGroupCallback: UpdateReceiver<ChannelPostMediaGroupUpdate>? = null,
    editedChannelPostCallback: UpdateReceiver<EditChannelPostUpdate>? = null,
    editedChannelPostMediaGroupCallback: UpdateReceiver<EditChannelPostMediaGroupUpdate>? = null,
    chosenInlineResultCallback: UpdateReceiver<ChosenInlineResultUpdate>? = null,
    inlineQueryCallback: UpdateReceiver<InlineQueryUpdate>? = null,
    callbackQueryCallback: UpdateReceiver<CallbackQueryUpdate>? = null,
    shippingQueryCallback: UpdateReceiver<ShippingQueryUpdate>? = null,
    preCheckoutQueryCallback: UpdateReceiver<PreCheckoutQueryUpdate>? = null,
    pollCallback: UpdateReceiver<PollUpdate>? = null,
    pollAnswerCallback: UpdateReceiver<PollAnswerUpdate>? = null,
    timeoutSeconds: Seconds = 30,
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
): Job {
    return startGettingOfUpdatesByLongPolling(
        SimpleUpdatesFilter(
            messageCallback,
            messageMediaGroupCallback,
            editedMessageCallback,
            editedMessageMediaGroupCallback,
            channelPostCallback,
            channelPostMediaGroupCallback,
            editedChannelPostCallback,
            editedChannelPostMediaGroupCallback,
            chosenInlineResultCallback,
            inlineQueryCallback,
            callbackQueryCallback,
            shippingQueryCallback,
            preCheckoutQueryCallback,
            pollCallback,
            pollAnswerCallback
        ),
        timeoutSeconds,
        exceptionsHandler,
        scope
    )
}

@Deprecated("Will be removed soon", ReplaceWith("longPolling", "dev.inmo.tgbotapi.extensions.utils.updates.retrieving.longPolling"))
@Suppress("unused")
fun RequestsExecutor.startGettingOfUpdatesByLongPolling(
    messageCallback: UpdateReceiver<MessageUpdate>? = null,
    mediaGroupCallback: UpdateReceiver<MediaGroupUpdate>? = null,
    editedMessageCallback: UpdateReceiver<EditMessageUpdate>? = null,
    channelPostCallback: UpdateReceiver<ChannelPostUpdate>? = null,
    editedChannelPostCallback: UpdateReceiver<EditChannelPostUpdate>? = null,
    chosenInlineResultCallback: UpdateReceiver<ChosenInlineResultUpdate>? = null,
    inlineQueryCallback: UpdateReceiver<InlineQueryUpdate>? = null,
    callbackQueryCallback: UpdateReceiver<CallbackQueryUpdate>? = null,
    shippingQueryCallback: UpdateReceiver<ShippingQueryUpdate>? = null,
    preCheckoutQueryCallback: UpdateReceiver<PreCheckoutQueryUpdate>? = null,
    pollCallback: UpdateReceiver<PollUpdate>? = null,
    pollAnswerCallback: UpdateReceiver<PollAnswerUpdate>? = null,
    timeoutSeconds: Seconds = 30,
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
): Job = startGettingOfUpdatesByLongPolling(
    messageCallback = messageCallback,
    messageMediaGroupCallback = mediaGroupCallback,
    editedMessageCallback = editedMessageCallback,
    editedMessageMediaGroupCallback = mediaGroupCallback,
    channelPostCallback = channelPostCallback,
    channelPostMediaGroupCallback = mediaGroupCallback,
    editedChannelPostCallback = editedChannelPostCallback,
    editedChannelPostMediaGroupCallback = mediaGroupCallback,
    chosenInlineResultCallback = chosenInlineResultCallback,
    inlineQueryCallback = inlineQueryCallback,
    callbackQueryCallback = callbackQueryCallback,
    shippingQueryCallback = shippingQueryCallback,
    preCheckoutQueryCallback = preCheckoutQueryCallback,
    pollCallback = pollCallback,
    pollAnswerCallback = pollAnswerCallback,
    timeoutSeconds = timeoutSeconds,
    exceptionsHandler = exceptionsHandler,
    scope = scope
)

