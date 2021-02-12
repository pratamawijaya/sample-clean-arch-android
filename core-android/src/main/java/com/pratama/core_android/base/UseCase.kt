package com.pratama.core_android.base

import com.pratama.core_android.Either
import com.pratama.core_android.exceptions.Failure

abstract class UseCase<out Type, in Params> where Type : Any {
    abstract suspend fun run(params: Params): Type

    class None
}