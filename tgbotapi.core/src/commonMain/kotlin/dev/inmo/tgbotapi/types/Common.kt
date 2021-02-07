package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.utils.BuiltinMimeTypes
import kotlinx.serialization.Serializable

@Serializable
value class Identifier(val id: Long)
@Serializable
value class MessageIdentifier(val value: Long)
@Serializable
value class InlineQueryIdentifier(val value: String)
@Serializable
value class UpdateIdentifier(val value: Long)
@Serializable
value class MediaGroupIdentifier(val value: String)
@Serializable
value class ForwardSignature(val value: String)
@Serializable
value class ForwardSenderName(val value: String)
@Serializable
value class AuthorSignature(val value: ForwardSignature)
@Serializable
value class CallbackQueryIdentifier(val value: String)
@Serializable
value class PaymentQueryIdentifier(val value: String)
@Serializable
value class PreCheckoutQueryId(val value: String)
@Serializable
value class ShippingQueryIdentifier(val value: String)
@Serializable
value class InvoicePayload(val value: String)
@Serializable
value class ShippingOptionIdentifier(val value: String)
@Serializable
value class StartParameter(val value: String)
@Serializable
value class InlineMessageIdentifier(val value: String)
@Serializable
value class PollIdentifier(val value: String)
@Serializable
value class StickerSetName(val value: String)
@Serializable
value class FileUniqueId(val value: String)
@Serializable
value class DiceResult(val value: Int)
@Serializable
value class FoursquareId(val value: String)
@Serializable
value class FoursquareType(val value: String)
@Serializable
value class GooglePlaceId(val value: String)
@Serializable
value class GooglePlaceType(val value: String)

@Serializable
value class Seconds(val value: Int)
@Serializable
value class MilliSeconds(val value: Long)
@Serializable
value class LongSeconds(val value: Long)

@Serializable
value class Meters(val value: Float)
@Serializable
value class Degrees(val value: Int)

val degreesLimit = 1 .. 360
val horizontalAccuracyLimit = 0F .. 1500F

val getUpdatesLimit = 1 .. 100
val callbackQueryAnswerLength = 0 until 200
val captionLength = 0 .. 1024
val textLength = 1 .. 4096
val userProfilePhotosRequestLimit = 0 .. 100
val chatTitleLength = 1 until 255
val chatDescriptionLength = 0 until 256
val inlineResultQueryIdLingth = 1 until 64
val allowedConnectionsLength = 1 .. 100

val invoiceTitleLimit = 1 until 32
val invoiceDescriptionLimit = 1 until 256
val invoicePayloadBytesLimit = 1 until 128

val pollOptionTextLength = 1 .. 100
val pollQuestionTextLength = 1 .. 300
val pollOptionsLimit = 2 .. 10

val livePeriodLimit = 60 .. 86400

val inlineQueryAnswerResultsLimit = 0 .. 50

val customTitleLength = 0 .. 16

val dartsCubeAndBowlingDiceResultLimit = 1 .. 6
val basketballAndFootballDiceResultLimit = 1 .. 5
val slotMachineDiceResultLimit = 1 .. 64

val botCommandLengthLimit = 1 .. 32
val botCommandLimit = botCommandLengthLimit
val botCommandDescriptionLimit = 3 .. 256
val botCommandsLimit = 0 .. 100

val mediaCountInMediaGroup: IntRange = 2 .. 10

val explanationLimit = 0 .. 200

val openPeriodPollSecondsLimit = 5 .. 600

// Made as lazy for correct work in K/JS
val telegramInlineModeGifPermittedMimeTypes by lazy {
    listOf(
        BuiltinMimeTypes.Image.Jpg,
        BuiltinMimeTypes.Image.Gif,
        BuiltinMimeTypes.Video.MP4
    )
}

const val chatIdField = "chat_id"
const val messageIdField = "message_id"
const val updateIdField = "update_id"
const val fromChatIdField = "from_chat_id"
const val disableWebPagePreviewField = "disable_web_page_preview"
const val disableNotificationField = "disable_notification"
const val replyToMessageIdField = "reply_to_message_id"
const val allowSendingWithoutReplyField = "allow_sending_without_reply"
const val replyMarkupField = "reply_markup"
const val disableContentTypeDetectionField = "disable_content_type_detection"
const val supportStreamingField = "support_streaming"
const val livePeriodField = "live_period"
const val proximityAlertRadiusField = "proximity_alert_radius"
const val isBotField = "is_bot"
const val firstNameField = "first_name"
const val lastNameField = "last_name"
const val languageCodeField = "language_code"
const val canJoinGroupsField = "can_join_groups"
const val canReadAllGroupMessagesField = "can_read_all_group_messages"
const val supportInlineQueriesField = "supports_inline_queries"
const val textEntitiesField = "text_entities"
const val entitiesField = "entities"
const val stickerSetNameField = "set_name"
const val stickerSetNameFullField = "sticker_set_name"
const val slowModeDelayField = "slow_mode_delay"
const val maskPositionField = "mask_position"
const val phoneNumberField = "phone_number"
const val userIdField = "user_id"
const val onlyIfBannedField = "only_if_banned"
const val containsMasksField = "contains_masks"
const val resultIdField = "result_id"
const val inlineMessageIdField = "inline_message_id"
const val callbackDataField = "callback_data"
const val callbackGameField = "callback_game"
const val callbackQueryIdField = "callback_query_id"
const val inlineQueryIdField = "inline_query_id"
const val inlineKeyboardField = "inline_keyboard"
const val showAlertField = "show_alert"
const val cacheTimeField = "cache_time"
const val foursquareIdField = "foursquare_id"
const val foursquareTypeField = "foursquare_type"
const val googlePlaceIdField = "google_place_id"
const val googlePlaceTypeField = "google_place_type"
const val untilDateField = "until_date"
const val errorMessageField = "error_message"
const val messageTextField = "message_text"
const val isPersonalField = "is_personal"
const val nextOffsetField = "next_offset"
const val switchPmTextField = "switch_pm_text"
const val switchPmParameterField = "switch_pm_parameter"
const val maxAllowedConnectionsField = "max_connections"
const val allowedUpdatesField = "allowed_updates"
const val dropPendingUpdatesField = "drop_pending_updates"
const val hasCustomCertificateField = "has_custom_certificate"
const val pendingUpdateCountField = "pending_update_count"
const val lastErrorDateField = "last_error_date"
const val lastErrorMessageField = "last_error_message"
const val votesCountField = "voter_count"
const val isClosedField = "is_closed"
const val totalVoterCountField = "total_voter_count"
const val correctOptionIdField = "correct_option_id"
const val allowsMultipleAnswersField = "allows_multiple_answers"
const val isAnonymousField = "is_anonymous"
const val captionEntitiesField = "caption_entities"
const val loginUrlField = "login_url"
const val forwardTextField = "forward_text"
const val botUsernameField = "bot_username"
const val switchInlineQueryCurrentChatField = "switch_inline_query_current_chat"
const val switchInlineQueryField = "switch_inline_query"
const val isAnimatedField = "is_animated"
const val inviteLinkField = "invite_link"
const val pinnedMessageField = "pinned_message"
const val customTitleField = "custom_title"
const val optionIdsField = "option_ids"
const val ipAddressField = "ip_address"
const val linkedChatIdField = "linked_chat_id"
const val horizontalAccuracyField = "horizontal_accuracy"

const val requestContactField = "request_contact"
const val requestLocationField = "request_location"
const val requestPollField = "request_poll"


const val requestWriteAccessField = "request_write_access"


const val photoUrlField = "photo_url"
const val photoSizeField = "photo_size"
const val photoFileIdField = "photo_file_id"
const val photoWidthField = "photo_width"
const val photoHeightField = "photo_height"

const val gifUrlField = "gif_url"
const val gifFileIdField = "gif_file_id"
const val gifWidthField = "gif_width"
const val gifHeightField = "gif_height"
const val gifDurationField = "gif_duration"

const val mpeg4GifUrlField = "mpeg4_url"
const val mpeg4GifFileIdField = "mpeg4_file_id"
const val mpeg4GifWidthField = "mpeg4_width"
const val mpeg4GifHeightField = "mpeg4_height"
const val mpeg4GifDurationField = "mpeg4_duration"

const val videoUrlField = "video_url"
const val videoFileIdField = "video_file_id"
const val videoWidthField = "video_width"
const val videoHeightField = "video_height"
const val videoDurationField = "video_duration"

const val audioUrlField = "audio_url"
const val audioFileIdField = "audio_file_id"
const val audioDurationField = "audio_duration"

const val voiceUrlField = "voice_url"
const val voiceFileIdField = "voice_file_id"
const val voiceDurationField = "voice_duration"

const val documentUrlField = "document_url"
const val documentFileIdField = "document_file_id"

const val stickerFileIdField = "sticker_file_id"

const val gameShortNameField = "game_short_name"

const val thumbUrlField = "thumb_url"
const val thumbMimeTypeField = "thumb_mime_type"
const val thumbWidthField = "thumb_width"
const val thumbHeightField = "thumb_height"

const val inputMessageContentField = "input_message_content"
const val hideUrlField = "hide_url"

const val botCommandField = "command"
const val botCommandsField = "commands"

const val isMemberField = "is_member"
const val canSendMessagesField = "can_send_messages"
const val canSendMediaMessagesField = "can_send_media_messages"
const val canSendOtherMessagesField = "can_send_other_messages"
const val canSendPollsField = "can_send_polls"
const val canAddWebPagePreviewsField = "can_add_web_page_previews"
const val canSetStickerSetField = "can_set_sticker_set"

const val statusField = "status"
const val canBeEditedField = "can_be_edited"
const val canChangeInfoField = "can_change_info"
const val canPostMessagesField = "can_post_messages"
const val canEditMessagesField = "can_edit_messages"
const val canDeleteMessagesField = "can_delete_messages"
const val canInviteUsersField = "can_invite_users"
const val canRestrictMembersField = "can_restrict_members"
const val canPinMessagesField = "can_pin_messages"
const val canPromoteMembersField = "can_promote_members"
const val pngStickerField = "png_sticker"
const val tgsStickerField = "tgs_sticker"

const val okField = "ok"
const val captionField = "caption"
const val explanationField = "explanation"
const val idField = "id"
const val pollIdField = "poll_id"
const val textField = "text"
const val thumbField = "thumb"
const val emojiField = "emoji"
const val emojisField = "emojis"
const val titleField = "title"
const val descriptionField = "description"
const val performerField = "performer"
const val durationField = "duration"
const val widthField = "width"
const val heightField = "height"
const val lengthField = "length"
const val latitudeField = "latitude"
const val longitudeField = "longitude"
const val headingField = "heading"
const val fromField = "from"
const val userField = "user"
const val dateField = "date"
const val chatField = "chat"
const val usernameField = "username"
const val bioField = "bio"
const val nameField = "name"
const val emailField = "email"
const val locationField = "location"
const val queryField = "query"
const val offsetField = "offset"
const val limitField = "limit"
const val stickersField = "stickers"
const val stickerField = "sticker"
const val urlField = "url"
const val addressField = "address"
const val actionField = "action"
const val positionField = "position"
const val labelField = "label"
const val amountField = "amount"
const val pricesField = "prices"
const val payloadField = "payload"
const val vcardField = "vcard"
const val resultsField = "results"
const val certificateField = "certificate"
const val questionField = "question"
const val optionsField = "options"
const val payField = "pay"
const val permissionsField = "permissions"
const val typeField = "type"
const val valueField = "value"

const val pointField = "point"
const val xShiftField = "x_shift"
const val yShiftField = "y_shift"
const val scaleField = "scale"

const val explanationEntitiesField = "explanation_entities"
const val explanationParseModeField = "explanation_parse_mode"
const val openPeriodField = "open_period"
const val closeDateField = "close_date"

const val smallFileIdField = "small_file_id"
const val bigFileIdField = "big_file_id"
const val smallFileUniqueIdField = "small_file_unique_id"
const val bigFileUniqueIdField = "big_file_unique_id"

const val fileUniqueIdField = "file_unique_id"


const val currencyField = "currency"
const val startParameterField = "start_parameter"
const val totalAmountField = "total_amount"
const val invoicePayloadField = "invoice_payload"
const val shippingOptionIdField = "shipping_option_id"
const val shippingQueryIdField = "shipping_query_id"
const val preCheckoutQueryIdField = "pre_checkout_query_id"
const val shippingOptionsField = "shipping_options"
const val countryCodeField = "country_code"
const val stateField = "state"
const val cityField = "city"
const val firstStreetLineField = "street_line1"
const val secondStreetLineField = "street_line2"
const val postCodeField = "post_code"
const val shippingAddressField = "shipping_address"
const val orderInfoField = "order_info"
const val telegramPaymentChargeIdField = "telegram_payment_charge_id"
const val providerPaymentChargeIdField = "provider_payment_charge_id"
const val providerTokenField = "provider_token"
const val providerDataField = "provider_data"

const val requireNameField = "need_name"
const val requirePhoneNumberField = "need_phone_number"
const val requireEmailField = "need_email"
const val requireShippingAddressField = "need_shipping_address"

const val shouldSendPhoneNumberToProviderField = "send_phone_number_to_provider"
const val shouldSendEmailToProviderField = "send_email_to_provider"

const val priceDependOnShipAddressField = "is_flexible"

const val documentField = "document"
const val photoField = "photo"
const val audioField = "audio"
const val videoField = "video"
const val animationField = "animation"
const val voiceField = "voice"
const val videoNoteField = "video_note"
const val mediaField = "media"

const val disableEditMessageField = "disable_edit_message"
const val scoreField = "score"
const val forceField = "force"

const val regularPollType = "regular"
const val quizPollType = "quiz"

const val dataField = "data"
const val credentialsField = "credentials"
const val hashField = "hash"
const val translationField = "translation"
const val translationFileField = "translation_file"
const val fileField = "file"
const val filesField = "files"
const val translationFilesField = "translation_files"
const val frontSideField = "front_side"
const val reverseSideField = "reverse_side"
const val selfieField = "selfie"
const val secretField = "secret"

const val errorsField = "errors"
const val sourceField = "source"
const val fieldNameField = "field_name"
const val dataHashField = "data_hash"
const val fileHashField = "file_hash"
const val fileHashesField = "file_hashes"
const val messageField = "message"
const val unspecifiedField = "unspecified"

const val secureDataField = "secure_data"
const val nonceField = "nonce"

const val personalDetailsField = "personal_details"
const val passportField = "passport"
const val internalPassportField = "internal_passport"
const val driverLicenseField = "driver_license"
const val identityCardField = "identity_card"
const val utilityBillField = "utility_bill"
const val bankStatementField = "bank_statement"
const val rentalAgreementField = "rental_agreement"
const val passportRegistrationField = "passport_registration"
const val temporaryRegistrationField = "temporary_registration"
