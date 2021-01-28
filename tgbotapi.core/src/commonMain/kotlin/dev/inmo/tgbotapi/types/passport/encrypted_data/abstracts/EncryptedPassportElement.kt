package dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts

import dev.inmo.micro_utils.crypto.SourceBytes
import dev.inmo.micro_utils.serialization.base64.Base64BytesToFromStringSerializer
import dev.inmo.tgbotapi.types.passport.encrypted_data.EncryptedElementSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

typealias PassportElementHash = SourceBytes

@Serializable(EncryptedElementSerializer::class)
interface EncryptedPassportElement {
    val hash: PassportElementHash
}

@Serializable(EncryptedElementSerializer::class)
data class UnknownEncryptedPassportElement(
    val rawJson: JsonObject,
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash
) : EncryptedPassportElement
