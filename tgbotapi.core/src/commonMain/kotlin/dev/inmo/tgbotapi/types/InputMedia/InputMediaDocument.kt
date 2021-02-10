package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.files.DocumentFile
import kotlinx.serialization.*

internal const val documentInputMediaType = "document"

fun InputMediaDocument(
    file: InputFile,
    caption: String? = null,
    parseMode: ParseMode? = null,
    thumb: InputFile? = null,
    disableContentTypeDetection: Boolean? = null
) = InputMediaDocument(file, caption, parseMode, null, thumb, disableContentTypeDetection)

fun InputMediaDocument(
    file: InputFile,
    entities: List<TextSource>,
    thumb: InputFile? = null,
    disableContentTypeDetection: Boolean? = null
) = InputMediaDocument(file, entities.makeString(), null, entities.toRawMessageEntities(), thumb, disableContentTypeDetection)

/**
 * Represents a general file to be sent. See https://core.telegram.org/bots/api#inputmediadocument
 *
 * @param disableContentTypeDetection Disables automatic server-side content type detection for files uploaded using
 * multipart/form-data. Always used by Telegram system as true, if the document is sent as part of an album.
 *
 * @see InputFile
 * @see MultipartFile
 * @see FileId
 */
@Serializable
data class InputMediaDocument internal constructor(
    override val file: InputFile,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    override val thumb: InputFile? = null,
    @SerialName(disableContentTypeDetectionField)
    val disableContentTypeDetection: Boolean? = null
) : InputMedia, DocumentMediaGroupMemberInputMedia, ThumbedInputMedia {
    override val type: String = documentInputMediaType
    override val entities: List<TextSource>? by lazy {
        rawEntities ?.asTextParts(text ?: return@lazy null) ?.justTextSources()
    }

    override fun serialize(format: StringFormat): String = format.encodeToString(serializer(), this)

    @SerialName(mediaField)
    override val media: String
    init { media = file.fileIdToSend } // crutch until js compiling will be fixed
}

fun DocumentFile.toInputMediaDocument(
    caption: String? = null,
    parseMode: ParseMode? = null
) = InputMediaDocument(
    fileId,
    caption,
    parseMode,
    thumb ?.fileId
)

fun DocumentFile.toInputMediaDocument(
    textSources: TextSourcesList = emptyList()
) = InputMediaDocument(
    fileId,
    textSources,
    thumb ?.fileId
)
