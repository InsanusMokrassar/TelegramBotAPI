package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InputMessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideo
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.mimeTypeField
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineQueryResultContact(
    @SerialName(idField)
    override val id: String,
    @SerialName(phoneNumberField)
    override val phoneNumber: String,
    @SerialName(firstNameField)
    override val firstName: String,
    @SerialName(lastNameField)
    @Optional
    override val lastName: String? = null,
    @SerialName(vcardField)
    @Optional
    override val vcard: String? = null,
    @SerialName(thumbUrlField)
    @Optional
    override val thumbUrl: String? = null,
    @SerialName(thumbWidthField)
    @Optional
    override val thumbWidth: Int? = null,
    @SerialName(thumbHeightField)
    @Optional
    override val thumbHeight: Int? = null,
    @SerialName(replyMarkupField)
    @Optional
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(inputMessageContentField)
    @Optional
    override val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult,
    CommonContactData,
    WithInputMessageContentInlineQueryResult,
    ThumbedInlineQueryResult,
    ThumbSizedInlineQueryResult
{
    override val type: String = "contact"
}