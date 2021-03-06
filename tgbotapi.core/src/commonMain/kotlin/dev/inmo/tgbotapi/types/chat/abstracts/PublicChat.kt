package dev.inmo.tgbotapi.types.chat.abstracts

import dev.inmo.tgbotapi.types.chat.PreviewChatSerializer
import kotlinx.serialization.Serializable

@Serializable(PreviewChatSerializer::class)
interface PublicChat : Chat {
    val title: String
}
