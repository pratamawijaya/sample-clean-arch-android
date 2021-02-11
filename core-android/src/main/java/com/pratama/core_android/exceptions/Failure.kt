package com.pratama.core_android.exceptions

sealed class Failure {
    object NetworkException : Failure()
    object ServerError : Failure()
    object LocalDataNotFound : Failure()
}