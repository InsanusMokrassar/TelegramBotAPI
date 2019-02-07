package com.github.insanusmokrassar.TelegramBotAPI.requests.games

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ByInlineMessageId
import com.github.insanusmokrassar.TelegramBotAPI.requests.games.abstracts.GetGameHighScores
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetGameHighScoresByInlineMessageId (
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(inlineMessageIdField)
    override val inlineMessageId: InlineMessageIdentifier
) : GetGameHighScores, ByInlineMessageId
