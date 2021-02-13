package com.pratama.samplecleanarch.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e
import com.pratama.samplecleanarch.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.swipeRefresh)

        viewModel.getTopheadlines("us", "technology")

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getTopheadlines("us", "technology")
        }

        viewModel.uiState().observe(this, { state ->
            when (state) {
                is MainViewModel.MainViewState.Loading ->{
                    swipeRefreshLayout.isRefreshing = true
                }

                is MainViewModel.MainViewState.Error -> {
                    e { "error -> ${state.message}" }
                }

                is MainViewModel.MainViewState.Success -> {
                    swipeRefreshLayout.isRefreshing = false
                    d { "sukses ${state.list.size}" }
                    state.list.map { d { "resultnya adalah ${it.title}" } }
                }
            }
        })
    }
}