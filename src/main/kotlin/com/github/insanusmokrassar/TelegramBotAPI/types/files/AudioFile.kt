package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.Performerable
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import kotlinx.serialization.*

@Serializable
data class AudioFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @Optional
    override val duration: Long? = null,
    @Optional
    override val performer: String? = null,
    @Optional
    override val title: String? = null,
    @SerialName(mimeTypeField)
    @Optional
    override val mimeType: String? = null,
    @SerialName(fileSizeField)
    @Optional
    override val fileSize: Long? = null,
    @Optional
    override val thumb: PhotoSize? = null
) : TelegramMediaFile, MimedMediaFile, ThumbedMediaFile, PlayableMediaFile, TitledMediaFile, Performerable
