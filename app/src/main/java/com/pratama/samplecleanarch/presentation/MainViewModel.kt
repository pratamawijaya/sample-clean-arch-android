package com.pratama.samplecleanarch.presentation

import androidx.lifecycle.viewModelScope
import com.github.ajalt.timberkt.d
import com.pratama.core_android.base.BaseViewModel
import com.pratama.core_android.exceptions.Failure
import com.pratama.samplecleanarch.domain.usecase.GetTopHeadlinesUseCase
import kotlinx.coroutines.launch

class MainViewModel constructor(private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase) :
    BaseViewModel<MainViewModel.MainViewState>() {

    sealed class MainViewState {
        data class ErrorState(val msg: String) : MainViewState()
    }

    fun getTopHeadlines() {
        viewModelScope.launch {
            val result = getTopHeadlinesUseCase.run(
                GetTopHeadlinesUseCase.Param(
                    category = "",
                    country = "",
                    page = 1
                )
            )

            result.fold(::handleError) { result ->
                result.map {
                    d { "resultnya ${it.title}" }
                }
            }
        }
    }


    override fun handleError(failure: Failure) {
        when (failure) {
            is Failure.ServerError -> {
                uiState.postValue(MainViewState.ErrorState("Server Error"))
            }

            is Failure.NetworkException -> {
                uiState.postValue(MainViewState.ErrorState("Network Error"))
            }

            is Failure.LocalDataNotFound -> {

            }
        }
    }
}