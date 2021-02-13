package com.pratama.samplecleanarch.presentation

import androidx.lifecycle.viewModelScope
import com.github.ajalt.timberkt.d
import com.pratama.core_android.base.BaseViewModel
import com.pratama.core_android.exceptions.Failure
import com.pratama.samplecleanarch.domain.entity.Article
import com.pratama.samplecleanarch.domain.usecase.GetTopHeadlinesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase) :
    BaseViewModel<MainViewModel.MainViewState>() {

    sealed class MainViewState {
        object Loading : MainViewState()
        data class Error(val message: String) : MainViewState()
        data class Success(val list: List<Article>) : MainViewState()
    }

    fun getTopheadlines(country: String, category: String) {
        uiState.postValue(MainViewState.Loading)
        viewModelScope.launch {
            val result =
                getTopHeadlinesUseCase.run(GetTopHeadlinesUseCase.Params(country, category))

            result.fold(::handleError) {
                uiState.postValue(MainViewState.Success(it))
            }
        }
    }

    override fun handleError(failure: Failure) {
        when (failure) {
            is Failure.ServerError -> {
                uiState.postValue(MainViewState.Error("server error : ${failure.message}"))
            }
        }
    }
}