package com.pratama.core_android.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pratama.core_android.exceptions.Failure

abstract class BaseViewModel<T> : ViewModel() {

    protected val uiState: MutableLiveData<T> = MutableLiveData()

    fun uiState(): LiveData<T> = uiState

    abstract fun handleError(failure: Failure)
}