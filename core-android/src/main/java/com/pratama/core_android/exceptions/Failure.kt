package com.pratama.core_android.exceptions

sealed class Failure {
    object NetworkException : Failure()
    data class ServerError(val message: String) : Failure()
    object LocalDataNotFound : Failure()
}