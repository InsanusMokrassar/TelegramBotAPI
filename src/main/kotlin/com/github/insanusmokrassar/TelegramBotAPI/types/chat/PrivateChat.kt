package com.github.insanusmokrassar.TelegramBotAPI.types.chat

import com.github.insanusmokrassar.TelegramBotAPI.types.*

data class PrivateChat(
    override val id: ChatId,
    val username: Username? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    override val chatPhoto: ChatPhoto? = null
) : Chat
