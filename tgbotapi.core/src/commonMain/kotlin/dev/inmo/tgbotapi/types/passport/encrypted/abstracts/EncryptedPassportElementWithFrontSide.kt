package dev.inmo.tgbotapi.types.passport.encrypted.abstracts

import dev.inmo.tgbotapi.types.passport.encrypted.EncryptedElementSerializer
import dev.inmo.tgbotapi.types.passport.encrypted.PassportFile
import kotlinx.serialization.Serializable

@Serializable(EncryptedElementSerializer::class)
interface EncryptedPassportElementWithFrontSide : EncryptedPassportElement {
    val frontSide: PassportFile?
}