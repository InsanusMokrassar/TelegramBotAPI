package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendTextMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat

suspend fun TelegramBot.sendMessage(
    chatId: ChatIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendTextMessage(chatId, text, parseMode, disableWebPagePreview, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)
)

suspend fun TelegramBot.sendTextMessage(
    chatId: ChatIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendMessage(
    chatId, text, parseMode, disableWebPagePreview, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup
)

suspend fun TelegramBot.sendMessage(
    chat: Chat,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendMessage(chat.id, text, parseMode, disableWebPagePreview, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)


suspend fun TelegramBot.sendTextMessage(
    chat: Chat,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(chat.id, text, parseMode, disableWebPagePreview, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend fun TelegramBot.sendMessage(
    chatId: ChatIdentifier,
    entities: TextSourcesList,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendTextMessage(chatId, entities, disableWebPagePreview, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)
)

suspend fun TelegramBot.sendTextMessage(
    chatId: ChatIdentifier,
    entities: TextSourcesList,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendMessage(
    chatId, entities, disableWebPagePreview, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup
)

suspend fun TelegramBot.sendMessage(
    chat: Chat,
    entities: TextSourcesList,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendMessage(chat.id, entities, disableWebPagePreview, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)


suspend fun TelegramBot.sendTextMessage(
    chat: Chat,
    entities: TextSourcesList,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(chat.id, entities, disableWebPagePreview, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)
