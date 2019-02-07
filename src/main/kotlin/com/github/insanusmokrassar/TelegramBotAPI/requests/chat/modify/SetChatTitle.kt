package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class SetChatTitle (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(titleField)
    val title: String
): ChatRequest, SimpleRequest<Boolean> {
    init {
        if (title.length !in chatTitleLength) {
            throw IllegalArgumentException("Chat title must be in $chatTitleLength range")
        }
    }

    override fun method(): String = "setChatTitle"
    override fun resultSerializer(): KSerializer<Boolean> = BooleanSerializer
}
