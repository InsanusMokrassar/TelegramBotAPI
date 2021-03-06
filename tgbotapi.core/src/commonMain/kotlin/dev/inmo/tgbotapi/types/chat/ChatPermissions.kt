package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatPermissions(
    @SerialName(canSendMessagesField)
    val canSendMessages: Boolean = false,
    @SerialName(canSendMediaMessagesField)
    val canSendMediaMessages: Boolean = false,
    @SerialName(canSendPollsField)
    val canSendPolls: Boolean = false,
    @SerialName(canSendOtherMessagesField)
    val canSendOtherMessages: Boolean = false,
    @SerialName(canAddWebPagePreviewsField)
    val canAddWebPagePreviews: Boolean = false,
    @SerialName(canChangeInfoField)
    val canChangeInfo: Boolean = false,
    @SerialName(canInviteUsersField)
    val canInviteUsers: Boolean = false,
    @SerialName(canPinMessagesField)
    val canPinMessages: Boolean = false
)

val LeftRestrictionsChatPermissions = ChatPermissions(
    canSendMessages = true,
    canSendMediaMessages = true,
    canSendPolls = true,
    canSendOtherMessages = true,
    canAddWebPagePreviews = true,
    canChangeInfo = true,
    canInviteUsers = true,
    canPinMessages = true,
)

val RestrictionsChatPermissions = ChatPermissions(
    canSendMessages = false,
    canSendMediaMessages = false,
    canSendPolls = false,
    canSendOtherMessages = false,
    canAddWebPagePreviews = false,
    canChangeInfo = false,
    canInviteUsers = false,
    canPinMessages = false,
)
