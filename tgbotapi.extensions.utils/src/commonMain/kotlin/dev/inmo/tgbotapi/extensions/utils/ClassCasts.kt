@file:Suppress("NOTHING_TO_INLINE")

package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.CallbackQuery.*
import dev.inmo.tgbotapi.types.ChatMember.*
import dev.inmo.tgbotapi.types.ChatMember.abstracts.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.*
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.*
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InlineQuery
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
import dev.inmo.tgbotapi.types.InlineQueries.query.BaseInlineQuery
import dev.inmo.tgbotapi.types.InlineQueries.query.LocationInlineQuery
import dev.inmo.tgbotapi.types.InputMedia.*
import dev.inmo.tgbotapi.types.actions.*
import dev.inmo.tgbotapi.types.buttons.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.*
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.chat.abstracts.*
import dev.inmo.tgbotapi.types.chat.abstracts.extended.*
import dev.inmo.tgbotapi.types.chat.extended.*
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.files.abstracts.*
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.abstracts.ChannelMessage
import dev.inmo.tgbotapi.types.message.abstracts.GroupEventMessage
import dev.inmo.tgbotapi.types.message.abstracts.SupergroupEventMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent
import dev.inmo.tgbotapi.types.message.content.abstracts.PossiblySentViaBotCommonMessage
import dev.inmo.tgbotapi.types.polls.*
import dev.inmo.tgbotapi.types.update.*
import dev.inmo.tgbotapi.types.update.MediaGroupUpdates.*
import dev.inmo.tgbotapi.types.update.abstracts.*

inline fun Chat.asBot(): Bot? = this as? Bot
inline fun Chat.asCommonBot(): CommonBot? = this as? CommonBot
inline fun Chat.asCommonUser(): CommonUser? = this as? CommonUser
inline fun Chat.asExtendedBot(): ExtendedBot? = this as? ExtendedBot
inline fun Chat.asUser(): User? = this as? User
inline fun Chat.asChannelChatImpl(): ChannelChatImpl? = this as? ChannelChatImpl
inline fun Chat.asGroupChatImpl(): GroupChatImpl? = this as? GroupChatImpl
inline fun Chat.asPrivateChatImpl(): PrivateChatImpl? = this as? PrivateChatImpl
inline fun Chat.asSupergroupChatImpl(): SupergroupChatImpl? = this as? SupergroupChatImpl
inline fun Chat.asChannelChat(): ChannelChat? = this as? ChannelChat
inline fun Chat.asGroupChat(): GroupChat? = this as? GroupChat
inline fun Chat.asPrivateChat(): PrivateChat? = this as? PrivateChat
inline fun Chat.asPublicChat(): PublicChat? = this as? PublicChat
inline fun Chat.asSuperPublicChat(): SuperPublicChat? = this as? SuperPublicChat
inline fun Chat.asSupergroupChat(): SupergroupChat? = this as? SupergroupChat
inline fun Chat.asUnknownChatType(): UnknownChatType? = this as? UnknownChatType
inline fun Chat.asUsernameChat(): UsernameChat? = this as? UsernameChat
inline fun Chat.asExtendedChannelChat(): ExtendedChannelChat? = this as? ExtendedChannelChat
inline fun Chat.asExtendedChat(): ExtendedChat? = this as? ExtendedChat
inline fun Chat.asExtendedGroupChat(): ExtendedGroupChat? = this as? ExtendedGroupChat
inline fun Chat.asExtendedPrivateChat(): ExtendedPrivateChat? = this as? ExtendedPrivateChat
inline fun Chat.asExtendedPublicChat(): ExtendedPublicChat? = this as? ExtendedPublicChat
inline fun Chat.asExtendedSupergroupChat(): ExtendedSupergroupChat? = this as? ExtendedSupergroupChat
inline fun Chat.asExtendedChannelChatImpl(): ExtendedChannelChatImpl? = this as? ExtendedChannelChatImpl
inline fun Chat.asExtendedGroupChatImpl(): ExtendedGroupChatImpl? = this as? ExtendedGroupChatImpl
inline fun Chat.asExtendedPrivateChatImpl(): ExtendedPrivateChatImpl? = this as? ExtendedPrivateChatImpl
inline fun Chat.asExtendedSupergroupChatImpl(): ExtendedSupergroupChatImpl? = this as? ExtendedSupergroupChatImpl
inline fun CallbackQuery.asDataCallbackQuery(): DataCallbackQuery? = this as? DataCallbackQuery
inline fun CallbackQuery.asGameShortNameCallbackQuery(): GameShortNameCallbackQuery? = this as? GameShortNameCallbackQuery
inline fun CallbackQuery.asInlineMessageIdCallbackQuery(): InlineMessageIdCallbackQuery? = this as? InlineMessageIdCallbackQuery
inline fun CallbackQuery.asInlineMessageIdDataCallbackQuery(): InlineMessageIdDataCallbackQuery? = this as? InlineMessageIdDataCallbackQuery
inline fun CallbackQuery.asInlineMessageIdGameShortNameCallbackQuery(): InlineMessageIdGameShortNameCallbackQuery? = this as? InlineMessageIdGameShortNameCallbackQuery
inline fun CallbackQuery.asMessageCallbackQuery(): MessageCallbackQuery? = this as? MessageCallbackQuery
inline fun CallbackQuery.asMessageDataCallbackQuery(): MessageDataCallbackQuery? = this as? MessageDataCallbackQuery
inline fun CallbackQuery.asMessageGameShortNameCallbackQuery(): MessageGameShortNameCallbackQuery? = this as? MessageGameShortNameCallbackQuery
inline fun CallbackQuery.asUnknownCallbackQueryType(): UnknownCallbackQueryType? = this as? UnknownCallbackQueryType
inline fun Message.asChannelEventMessage(): ChannelEventMessage<ChannelEvent>? = (this as? ChannelEventMessage<*>) ?.let { it as ChannelEventMessage<ChannelEvent> }
inline fun Message.asChannelMediaGroupMessage(): ChannelMediaGroupMessage? = this as? ChannelMediaGroupMessage
inline fun Message.asCommonGroupEventMessage(): CommonGroupEventMessage<GroupEvent>? = (this as? CommonGroupEventMessage<*>) ?.let { it as CommonGroupEventMessage<GroupEvent> }
inline fun Message.asCommonMediaGroupMessage(): CommonMediaGroupMessage? = this as? CommonMediaGroupMessage
inline fun Message.asCommonSupergroupEventMessage(): CommonSupergroupEventMessage<SupergroupEvent>? = (this as? CommonSupergroupEventMessage<*>) ?.let { it as CommonSupergroupEventMessage<SupergroupEvent> }
inline fun Message.asAnonymousGroupMessage(): AnonymousGroupMessage<MessageContent>? = (this as? AnonymousGroupMessage<*>) ?.let { it as AnonymousGroupMessage<MessageContent> }
inline fun Message.asChannelMessage(): ChannelMessage<MessageContent>? = (this as? ChannelMessage<*>) ?.let { it as ChannelMessage<MessageContent> }
inline fun Message.asChatEventMessage(): ChatEventMessage<ChatEvent>? = (this as? ChatEventMessage<*>) ?.let { it as ChatEventMessage<ChatEvent> }
inline fun Message.asCommonGroupMessage(): CommonGroupMessage<MessageContent>? = (this as? CommonGroupMessage<*>) ?.let { it as CommonGroupMessage<MessageContent> }
inline fun Message.asCommonMessage(): CommonMessage<MessageContent>? = (this as? CommonMessage<*>) ?.let { it as CommonMessage<MessageContent> }
inline fun Message.asContentMessage(): ContentMessage<MessageContent>? = (this as? ContentMessage<*>) ?.let { it as ContentMessage<MessageContent> }
inline fun Message.asFromChannelGroupMessage(): FromChannelGroupMessage<MessageContent>? = (this as? FromChannelGroupMessage<*>) ?.let { it as FromChannelGroupMessage<MessageContent> }
inline fun Message.asGroupEventMessage(): GroupEventMessage<GroupEvent>? = (this as? GroupEventMessage<*>) ?.let { it as GroupEventMessage<GroupEvent> }
inline fun Message.asGroupMessage(): GroupMessage<MessageContent>? = (this as? GroupMessage<*>) ?.let { it as GroupMessage<MessageContent> }
inline fun Message.asMediaGroupMessage(): MediaGroupMessage? = this as? MediaGroupMessage
inline fun Message.asPossiblyEditedMessage(): PossiblyEditedMessage? = this as? PossiblyEditedMessage
inline fun Message.asPossiblyForwardedMessage(): PossiblyForwardedMessage? = this as? PossiblyForwardedMessage
inline fun Message.asPossiblyPaymentMessage(): PossiblyPaymentMessage? = this as? PossiblyPaymentMessage
inline fun Message.asPrivateMessage(): PrivateMessage<MessageContent>? = (this as? PrivateMessage<*>) ?.let { it as PrivateMessage<MessageContent> }
inline fun Message.asPublicMessage(): PublicMessage<MessageContent>? = (this as? PublicMessage<*>) ?.let { it as PublicMessage<MessageContent> }
inline fun Message.asSignedMessage(): SignedMessage? = this as? SignedMessage
inline fun Message.asSupergroupEventMessage(): SupergroupEventMessage<SupergroupEvent>? = (this as? SupergroupEventMessage<*>) ?.let { it as SupergroupEventMessage<SupergroupEvent> }
inline fun Message.asUnknownMessageType(): UnknownMessageType? = this as? UnknownMessageType
inline fun Message.asPossiblySentViaBotCommonMessage(): PossiblySentViaBotCommonMessage<MessageContent>? = (this as? PossiblySentViaBotCommonMessage<*>) ?.let { it as PossiblySentViaBotCommonMessage<MessageContent> }
inline fun BotAction.asFindLocationAction(): FindLocationAction? = this as? FindLocationAction
inline fun BotAction.asRecordAudioAction(): RecordAudioAction? = this as? RecordAudioAction
inline fun BotAction.asRecordVideoAction(): RecordVideoAction? = this as? RecordVideoAction
inline fun BotAction.asRecordVideoNoteAction(): RecordVideoNoteAction? = this as? RecordVideoNoteAction
inline fun BotAction.asTypingAction(): TypingAction? = this as? TypingAction
inline fun BotAction.asUploadAudioAction(): UploadAudioAction? = this as? UploadAudioAction
inline fun BotAction.asUploadDocumentAction(): UploadDocumentAction? = this as? UploadDocumentAction
inline fun BotAction.asUploadPhotoAction(): UploadPhotoAction? = this as? UploadPhotoAction
inline fun BotAction.asUploadVideoAction(): UploadVideoAction? = this as? UploadVideoAction
inline fun BotAction.asUploadVideoNoteAction(): UploadVideoNoteAction? = this as? UploadVideoNoteAction
inline fun InlineQuery.asBaseInlineQuery(): BaseInlineQuery? = this as? BaseInlineQuery
inline fun InlineQuery.asLocationInlineQuery(): LocationInlineQuery? = this as? LocationInlineQuery
inline fun InputMessageContent.asInputContactMessageContent(): InputContactMessageContent? = this as? InputContactMessageContent
inline fun InputMessageContent.asInputLocationMessageContent(): InputLocationMessageContent? = this as? InputLocationMessageContent
inline fun InputMessageContent.asInputTextMessageContent(): InputTextMessageContent? = this as? InputTextMessageContent
inline fun InputMessageContent.asInputVenueMessageContent(): InputVenueMessageContent? = this as? InputVenueMessageContent
inline fun InlineQueryResult.asInlineQueryResultArticle(): InlineQueryResultArticle? = this as? InlineQueryResultArticle
inline fun InlineQueryResult.asInlineQueryResultAudioCachedImpl(): InlineQueryResultAudioCachedImpl? = this as? InlineQueryResultAudioCachedImpl
inline fun InlineQueryResult.asInlineQueryResultAudioImpl(): InlineQueryResultAudioImpl? = this as? InlineQueryResultAudioImpl
inline fun InlineQueryResult.asInlineQueryResultContact(): InlineQueryResultContact? = this as? InlineQueryResultContact
inline fun InlineQueryResult.asInlineQueryResultDocumentCachedImpl(): InlineQueryResultDocumentCachedImpl? = this as? InlineQueryResultDocumentCachedImpl
inline fun InlineQueryResult.asInlineQueryResultDocumentImpl(): InlineQueryResultDocumentImpl? = this as? InlineQueryResultDocumentImpl
inline fun InlineQueryResult.asInlineQueryResultGame(): InlineQueryResultGame? = this as? InlineQueryResultGame
inline fun InlineQueryResult.asInlineQueryResultGifCachedImpl(): InlineQueryResultGifCachedImpl? = this as? InlineQueryResultGifCachedImpl
inline fun InlineQueryResult.asInlineQueryResultGifImpl(): InlineQueryResultGifImpl? = this as? InlineQueryResultGifImpl
inline fun InlineQueryResult.asInlineQueryResultLocation(): InlineQueryResultLocation? = this as? InlineQueryResultLocation
inline fun InlineQueryResult.asInlineQueryResultMpeg4GifCachedImpl(): InlineQueryResultMpeg4GifCachedImpl? = this as? InlineQueryResultMpeg4GifCachedImpl
inline fun InlineQueryResult.asInlineQueryResultMpeg4GifImpl(): InlineQueryResultMpeg4GifImpl? = this as? InlineQueryResultMpeg4GifImpl
inline fun InlineQueryResult.asInlineQueryResultPhotoCachedImpl(): InlineQueryResultPhotoCachedImpl? = this as? InlineQueryResultPhotoCachedImpl
inline fun InlineQueryResult.asInlineQueryResultPhotoImpl(): InlineQueryResultPhotoImpl? = this as? InlineQueryResultPhotoImpl
inline fun InlineQueryResult.asInlineQueryResultStickerCached(): InlineQueryResultStickerCached? = this as? InlineQueryResultStickerCached
inline fun InlineQueryResult.asInlineQueryResultVenue(): InlineQueryResultVenue? = this as? InlineQueryResultVenue
inline fun InlineQueryResult.asInlineQueryResultVideoCachedImpl(): InlineQueryResultVideoCachedImpl? = this as? InlineQueryResultVideoCachedImpl
inline fun InlineQueryResult.asInlineQueryResultVideoImpl(): InlineQueryResultVideoImpl? = this as? InlineQueryResultVideoImpl
inline fun InlineQueryResult.asInlineQueryResultVoiceCachedImpl(): InlineQueryResultVoiceCachedImpl? = this as? InlineQueryResultVoiceCachedImpl
inline fun InlineQueryResult.asInlineQueryResultVoiceImpl(): InlineQueryResultVoiceImpl? = this as? InlineQueryResultVoiceImpl
inline fun InlineQueryResult.asDescribedInlineQueryResult(): DescribedInlineQueryResult? = this as? DescribedInlineQueryResult
inline fun InlineQueryResult.asFileInlineQueryResult(): FileInlineQueryResult? = this as? FileInlineQueryResult
inline fun InlineQueryResult.asOptionallyTitledInlineQueryResult(): OptionallyTitledInlineQueryResult? = this as? OptionallyTitledInlineQueryResult
inline fun InlineQueryResult.asSizedInlineQueryResult(): SizedInlineQueryResult? = this as? SizedInlineQueryResult
inline fun InlineQueryResult.asThumbSizedInlineQueryResult(): ThumbSizedInlineQueryResult? = this as? ThumbSizedInlineQueryResult
inline fun InlineQueryResult.asThumbedInlineQueryResult(): ThumbedInlineQueryResult? = this as? ThumbedInlineQueryResult
inline fun InlineQueryResult.asThumbedWithMimeTypeInlineQueryResult(): ThumbedWithMimeTypeInlineQueryResult? = this as? ThumbedWithMimeTypeInlineQueryResult
inline fun InlineQueryResult.asTitledInlineQueryResult(): TitledInlineQueryResult? = this as? TitledInlineQueryResult
inline fun InlineQueryResult.asUrlInlineQueryResult(): UrlInlineQueryResult? = this as? UrlInlineQueryResult
inline fun InlineQueryResult.asWithInputMessageContentInlineQueryResult(): WithInputMessageContentInlineQueryResult? = this as? WithInputMessageContentInlineQueryResult
inline fun InlineQueryResult.asInlineQueryResultAudio(): InlineQueryResultAudio? = this as? InlineQueryResultAudio
inline fun InlineQueryResult.asInlineQueryResultAudioCached(): InlineQueryResultAudioCached? = this as? InlineQueryResultAudioCached
inline fun InlineQueryResult.asInlineQueryResultAudioCommon(): InlineQueryResultAudioCommon? = this as? InlineQueryResultAudioCommon
inline fun InlineQueryResult.asInlineQueryResultDocument(): InlineQueryResultDocument? = this as? InlineQueryResultDocument
inline fun InlineQueryResult.asInlineQueryResultDocumentCached(): InlineQueryResultDocumentCached? = this as? InlineQueryResultDocumentCached
inline fun InlineQueryResult.asInlineQueryResultDocumentCommon(): InlineQueryResultDocumentCommon? = this as? InlineQueryResultDocumentCommon
inline fun InlineQueryResult.asInlineQueryResultGif(): InlineQueryResultGif? = this as? InlineQueryResultGif
inline fun InlineQueryResult.asInlineQueryResultGifCached(): InlineQueryResultGifCached? = this as? InlineQueryResultGifCached
inline fun InlineQueryResult.asInlineQueryResultGifCommon(): InlineQueryResultGifCommon? = this as? InlineQueryResultGifCommon
inline fun InlineQueryResult.asInlineQueryResultMpeg4Gif(): InlineQueryResultMpeg4Gif? = this as? InlineQueryResultMpeg4Gif
inline fun InlineQueryResult.asInlineQueryResultMpeg4GifCached(): InlineQueryResultMpeg4GifCached? = this as? InlineQueryResultMpeg4GifCached
inline fun InlineQueryResult.asInlineQueryResultMpeg4GifCommon(): InlineQueryResultMpeg4GifCommon? = this as? InlineQueryResultMpeg4GifCommon
inline fun InlineQueryResult.asInlineQueryResultPhoto(): InlineQueryResultPhoto? = this as? InlineQueryResultPhoto
inline fun InlineQueryResult.asInlineQueryResultPhotoCached(): InlineQueryResultPhotoCached? = this as? InlineQueryResultPhotoCached
inline fun InlineQueryResult.asInlineQueryResultPhotoCommon(): InlineQueryResultPhotoCommon? = this as? InlineQueryResultPhotoCommon
inline fun InlineQueryResult.asInlineQueryResultVideo(): InlineQueryResultVideo? = this as? InlineQueryResultVideo
inline fun InlineQueryResult.asInlineQueryResultVideoCached(): InlineQueryResultVideoCached? = this as? InlineQueryResultVideoCached
inline fun InlineQueryResult.asInlineQueryResultVideoCommon(): InlineQueryResultVideoCommon? = this as? InlineQueryResultVideoCommon
inline fun InlineQueryResult.asInlineQueryResultVoice(): InlineQueryResultVoice? = this as? InlineQueryResultVoice
inline fun InlineQueryResult.asInlineQueryResultVoiceCached(): InlineQueryResultVoiceCached? = this as? InlineQueryResultVoiceCached
inline fun InlineQueryResult.asInlineQueryResultVoiceCommon(): InlineQueryResultVoiceCommon? = this as? InlineQueryResultVoiceCommon
inline fun ChatMember.asAdministratorChatMemberImpl(): AdministratorChatMemberImpl? = this as? AdministratorChatMemberImpl
inline fun ChatMember.asCreatorChatMember(): CreatorChatMember? = this as? CreatorChatMember
inline fun ChatMember.asKickedChatMember(): KickedChatMember? = this as? KickedChatMember
inline fun ChatMember.asLeftChatMember(): LeftChatMember? = this as? LeftChatMember
inline fun ChatMember.asMemberChatMember(): MemberChatMember? = this as? MemberChatMember
inline fun ChatMember.asRestrictedChatMember(): RestrictedChatMember? = this as? RestrictedChatMember
inline fun ChatMember.asAdministratorChatMember(): AdministratorChatMember? = this as? AdministratorChatMember
inline fun ChatMember.asBannedChatMember(): BannedChatMember? = this as? BannedChatMember
inline fun ChatMember.asSpecialRightsChatMember(): SpecialRightsChatMember? = this as? SpecialRightsChatMember
inline fun InputMedia.asAudioMediaGroupMemberInputMedia(): AudioMediaGroupMemberInputMedia? = this as? AudioMediaGroupMemberInputMedia
inline fun InputMedia.asDocumentMediaGroupMemberInputMedia(): DocumentMediaGroupMemberInputMedia? = this as? DocumentMediaGroupMemberInputMedia
inline fun InputMedia.asDuratedInputMedia(): DuratedInputMedia? = this as? DuratedInputMedia
inline fun InputMedia.asInputMediaAnimation(): InputMediaAnimation? = this as? InputMediaAnimation
inline fun InputMedia.asInputMediaAudio(): InputMediaAudio? = this as? InputMediaAudio
inline fun InputMedia.asInputMediaDocument(): InputMediaDocument? = this as? InputMediaDocument
inline fun InputMedia.asInputMediaPhoto(): InputMediaPhoto? = this as? InputMediaPhoto
inline fun InputMedia.asInputMediaVideo(): InputMediaVideo? = this as? InputMediaVideo
inline fun InputMedia.asMediaGroupMemberInputMedia(): MediaGroupMemberInputMedia? = this as? MediaGroupMemberInputMedia
inline fun InputMedia.asSizedInputMedia(): SizedInputMedia? = this as? SizedInputMedia
inline fun InputMedia.asThumbedInputMedia(): ThumbedInputMedia? = this as? ThumbedInputMedia
inline fun InputMedia.asTitledInputMedia(): TitledInputMedia? = this as? TitledInputMedia
inline fun InputMedia.asVisualMediaGroupMemberInputMedia(): VisualMediaGroupMemberInputMedia? = this as? VisualMediaGroupMemberInputMedia
inline fun Update.asCallbackQueryUpdate(): CallbackQueryUpdate? = this as? CallbackQueryUpdate
inline fun Update.asChannelPostUpdate(): ChannelPostUpdate? = this as? ChannelPostUpdate
inline fun Update.asChosenInlineResultUpdate(): ChosenInlineResultUpdate? = this as? ChosenInlineResultUpdate
inline fun Update.asEditChannelPostUpdate(): EditChannelPostUpdate? = this as? EditChannelPostUpdate
inline fun Update.asEditMessageUpdate(): EditMessageUpdate? = this as? EditMessageUpdate
inline fun Update.asInlineQueryUpdate(): InlineQueryUpdate? = this as? InlineQueryUpdate
inline fun Update.asChannelPostMediaGroupUpdate(): ChannelPostMediaGroupUpdate? = this as? ChannelPostMediaGroupUpdate
inline fun Update.asEditChannelPostMediaGroupUpdate(): EditChannelPostMediaGroupUpdate? = this as? EditChannelPostMediaGroupUpdate
inline fun Update.asEditMediaGroupUpdate(): EditMediaGroupUpdate? = this as? EditMediaGroupUpdate
inline fun Update.asEditMessageMediaGroupUpdate(): EditMessageMediaGroupUpdate? = this as? EditMessageMediaGroupUpdate
inline fun Update.asMediaGroupUpdate(): MediaGroupUpdate? = this as? MediaGroupUpdate
inline fun Update.asMessageMediaGroupUpdate(): MessageMediaGroupUpdate? = this as? MessageMediaGroupUpdate
inline fun Update.asSentMediaGroupUpdate(): SentMediaGroupUpdate? = this as? SentMediaGroupUpdate
inline fun Update.asMessageUpdate(): MessageUpdate? = this as? MessageUpdate
inline fun Update.asPollAnswerUpdate(): PollAnswerUpdate? = this as? PollAnswerUpdate
inline fun Update.asPollUpdate(): PollUpdate? = this as? PollUpdate
inline fun Update.asPreCheckoutQueryUpdate(): PreCheckoutQueryUpdate? = this as? PreCheckoutQueryUpdate
inline fun Update.asShippingQueryUpdate(): ShippingQueryUpdate? = this as? ShippingQueryUpdate
inline fun Update.asBaseEditMessageUpdate(): BaseEditMessageUpdate? = this as? BaseEditMessageUpdate
inline fun Update.asBaseMessageUpdate(): BaseMessageUpdate? = this as? BaseMessageUpdate
inline fun Update.asBaseSentMessageUpdate(): BaseSentMessageUpdate? = this as? BaseSentMessageUpdate
inline fun Update.asUnknownUpdate(): UnknownUpdate? = this as? UnknownUpdate
inline fun TelegramMediaFile.asAnimationFile(): AnimationFile? = this as? AnimationFile
inline fun TelegramMediaFile.asAudioFile(): AudioFile? = this as? AudioFile
inline fun TelegramMediaFile.asDocumentFile(): DocumentFile? = this as? DocumentFile
inline fun TelegramMediaFile.asFile(): File? = this as? File
inline fun TelegramMediaFile.asPathedFile(): PathedFile? = this as? PathedFile
inline fun TelegramMediaFile.asPhotoSize(): PhotoSize? = this as? PhotoSize
inline fun TelegramMediaFile.asSticker(): Sticker? = this as? Sticker
inline fun TelegramMediaFile.asVideoFile(): VideoFile? = this as? VideoFile
inline fun TelegramMediaFile.asVideoNoteFile(): VideoNoteFile? = this as? VideoNoteFile
inline fun TelegramMediaFile.asVoiceFile(): VoiceFile? = this as? VoiceFile
inline fun TelegramMediaFile.asMimedMediaFile(): MimedMediaFile? = this as? MimedMediaFile
inline fun TelegramMediaFile.asPlayableMediaFile(): PlayableMediaFile? = this as? PlayableMediaFile
inline fun TelegramMediaFile.asSizedMediaFile(): SizedMediaFile? = this as? SizedMediaFile
inline fun TelegramMediaFile.asThumbedMediaFile(): ThumbedMediaFile? = this as? ThumbedMediaFile
inline fun KeyboardMarkup.asForceReply(): ForceReply? = this as? ForceReply
inline fun KeyboardMarkup.asInlineKeyboardMarkup(): InlineKeyboardMarkup? = this as? InlineKeyboardMarkup
inline fun KeyboardMarkup.asReplyKeyboardMarkup(): ReplyKeyboardMarkup? = this as? ReplyKeyboardMarkup
inline fun KeyboardMarkup.asReplyKeyboardRemove(): ReplyKeyboardRemove? = this as? ReplyKeyboardRemove
inline fun InlineKeyboardButton.asCallbackDataInlineKeyboardButton(): CallbackDataInlineKeyboardButton? = this as? CallbackDataInlineKeyboardButton
inline fun InlineKeyboardButton.asCallbackGameInlineKeyboardButton(): CallbackGameInlineKeyboardButton? = this as? CallbackGameInlineKeyboardButton
inline fun InlineKeyboardButton.asLoginURLInlineKeyboardButton(): LoginURLInlineKeyboardButton? = this as? LoginURLInlineKeyboardButton
inline fun InlineKeyboardButton.asPayInlineKeyboardButton(): PayInlineKeyboardButton? = this as? PayInlineKeyboardButton
inline fun InlineKeyboardButton.asSwitchInlineQueryCurrentChatInlineKeyboardButton(): SwitchInlineQueryCurrentChatInlineKeyboardButton? = this as? SwitchInlineQueryCurrentChatInlineKeyboardButton
inline fun InlineKeyboardButton.asSwitchInlineQueryInlineKeyboardButton(): SwitchInlineQueryInlineKeyboardButton? = this as? SwitchInlineQueryInlineKeyboardButton
inline fun InlineKeyboardButton.asURLInlineKeyboardButton(): URLInlineKeyboardButton? = this as? URLInlineKeyboardButton
inline fun InlineKeyboardButton.asUnknownInlineKeyboardButton(): UnknownInlineKeyboardButton? = this as? UnknownInlineKeyboardButton
inline fun Poll.asMultipleAnswersPoll(): MultipleAnswersPoll? = this as? MultipleAnswersPoll
inline fun Poll.asQuizPoll(): QuizPoll? = this as? QuizPoll
inline fun Poll.asRegularPoll(): RegularPoll? = this as? RegularPoll
inline fun Poll.asUnknownPollType(): UnknownPollType? = this as? UnknownPollType