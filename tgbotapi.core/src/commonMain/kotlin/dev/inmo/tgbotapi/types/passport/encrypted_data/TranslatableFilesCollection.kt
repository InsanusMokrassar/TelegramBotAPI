package dev.inmo.tgbotapi.types.passport.encrypted_data

import dev.inmo.micro_utils.serialization.base64.Base64BytesToFromStringSerializer
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(EncryptedElementSerializer::class)
sealed class TranslatableFilesCollection : Translatable, FilesCollection

@Serializable
data class UtilityBill(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile> = emptyList(),
    @SerialName(hashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash
) : TranslatableFilesCollection()
@Serializable
data class BankStatement(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile> = emptyList(),
    @SerialName(hashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash
) : TranslatableFilesCollection()
@Serializable
data class RentalAgreement(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile> = emptyList(),
    @SerialName(hashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash
) : TranslatableFilesCollection()
@Serializable
data class PassportRegistration(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile> = emptyList(),
    @SerialName(hashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash
) : TranslatableFilesCollection()
@Serializable
data class TemporaryRegistration(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile> = emptyList(),
    @SerialName(hashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash
) : TranslatableFilesCollection()

