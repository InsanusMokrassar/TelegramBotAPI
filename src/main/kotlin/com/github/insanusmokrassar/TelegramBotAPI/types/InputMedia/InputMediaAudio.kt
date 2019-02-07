package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.Performerable
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.mediaField
import kotlinx.serialization.*

@Serializable
data class InputMediaAudio(
    @Transient
    override val file: InputFile = throw IllegalStateException("Must be created with file"),
    @Optional
    override val caption: String? = null,
    @SerialName(parseModeField)
    @Optional
    override val parseMode: ParseMode? = null,
    @Optional
    override val duration: Long? = null,
    @Optional
    override val performer: String? = null,
    @Optional
    override val title: String? = null,
    override val thumb: InputFile? = null
) : InputMedia, DuratedInputMedia, ThumbedInputMedia, TitledInputMedia, CaptionedInputMedia, Performerable {
    override val type: String = "audio"

    @SerialName(mediaField)
    val media: String
        get() = file.let {
            when (it) {
                is FileId -> it.fileId
                is MultipartFile -> inputMediaFileAttachmentNameTemplate.format(it.fileId)
            }
        }
}
