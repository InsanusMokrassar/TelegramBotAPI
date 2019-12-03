package com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions

import com.github.insanusmokrassar.TelegramBotAPI.types.Response
import kotlinx.io.errors.IOException

fun newRequestException(
    response: Response,
    plainAnswer: String,
    message: String? = null,
    cause: Throwable? = null
) = when (response.description) {
    "Bad Request: reply message not found" -> ReplyMessageNotFoundException(response, plainAnswer, message, cause)
    "Unauthorized" -> UnauthorizedException(response, plainAnswer, message, cause)
    else -> CommonRequestException(response, plainAnswer, message, cause)
}

sealed class RequestException constructor(
    val response: Response,
    val plainAnswer: String,
    message: String? = null,
    cause: Throwable? = null
) : IOException(
    message ?: "Something went wrong",
    cause
)

class CommonRequestException(response: Response, plainAnswer: String, message: String?, cause: Throwable?) :
    RequestException(response, plainAnswer, message, cause)

class UnauthorizedException(response: Response, plainAnswer: String, message: String?, cause: Throwable?) :
    RequestException(response, plainAnswer, message, cause)

class ReplyMessageNotFoundException(response: Response, plainAnswer: String, message: String?, cause: Throwable?) :
    RequestException(response, plainAnswer, message, cause)