package dev.inmo.tgbotapi.types.message.content.abstracts

import dev.inmo.tgbotapi.CommonAbstracts.TextedInput
import dev.inmo.tgbotapi.types.InputMedia.*

interface MediaGroupContent : MediaContent, TextedInput {
    fun toMediaGroupMemberInputMedia(): MediaGroupMemberInputMedia
}

interface VisualMediaGroupContent : MediaGroupContent {
    override fun toMediaGroupMemberInputMedia(): VisualMediaGroupMemberInputMedia
}
interface AudioMediaGroupContent : MediaGroupContent {
    override fun toMediaGroupMemberInputMedia(): AudioMediaGroupMemberInputMedia
}
interface DocumentMediaGroupContent : MediaGroupContent {
    override fun toMediaGroupMemberInputMedia(): DocumentMediaGroupMemberInputMedia
}
