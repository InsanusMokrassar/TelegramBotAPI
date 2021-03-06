package dev.inmo.tgbotapi.types.passport.decrypted

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.passport.credentials.*
import dev.inmo.tgbotapi.types.passport.decrypted.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class IdentityWithReverseSideSecureValue : SecureValueIdentity, SecureValueWithData, SecureValueWithTranslations, SecureValueWithReverseSide {
    override val credentials: List<EndDataCredentials>
        get() = listOfNotNull(data, frontSide, reverseSide, selfie) + translation
}

@Serializable
data class DriverLicenseSecureValue(
    @SerialName(dataField)
    override val data: DataCredentials? = null,
    @SerialName(frontSideField)
    override val frontSide: FileCredentials? = null,
    @SerialName(reverseSideField)
    override val reverseSide: FileCredentials? = null,
    @SerialName(selfieField)
    override val selfie: FileCredentials? = null,
    @SerialName(translationField)
    override val translation: List<FileCredentials> = emptyList()
) : IdentityWithReverseSideSecureValue()

@Serializable
data class IdentityCardSecureValue(
    @SerialName(dataField)
    override val data: DataCredentials? = null,
    @SerialName(frontSideField)
    override val frontSide: FileCredentials? = null,
    @SerialName(reverseSideField)
    override val reverseSide: FileCredentials? = null,
    @SerialName(selfieField)
    override val selfie: FileCredentials? = null,
    @SerialName(translationField)
    override val translation: List<FileCredentials> = emptyList()
) : IdentityWithReverseSideSecureValue()
