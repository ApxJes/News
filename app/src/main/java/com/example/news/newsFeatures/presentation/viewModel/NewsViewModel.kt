package com.example.news.newsFeatures.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.core.Resource
import com.example.news.newsFeatures.presentation.state.NewsState
import com.example.news.newsFeatures.useCase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
): ViewModel(){

    private val _state: MutableStateFlow<NewsState> = MutableStateFlow(NewsState())
    val state = _state.asStateFlow()

    private val _event: MutableSharedFlow<UiEvent> = MutableSharedFlow<UiEvent>()
    val event = _event.asSharedFlow()

    init {
        getNews()
    }

    fun getNews() {
        viewModelScope.launch {
            getNewsUseCase()
                .buffer()
                .onEach {response ->
                    when(response) {
                        is Resource.Success -> {
                            _state.value = _state.value.copy(
                                newsState = response.data ?: emptyList(),
                                isLoading = false
                            )
                        }

                        is Resource.Loading -> {
                            _state.value = _state.value.copy(
                                newsState = response.data ?: emptyList(),
                                isLoading = true
                            )
                        }

                        is Resource.Error -> {
                            _state.value = _state.value.copy(
                                newsState = response.data ?: emptyList(),
                                isLoading = false
                            )

                            val errorMsg = response.message ?: "Unknown error"
                            Log.e("NewsViewModel", "Error fetching news: $errorMsg")

                            _event.emit(UiEvent.ToastMessage(
                                response.message ?: "Unknown error"
                            ))
                        }
                    }
                }
                .collect()
        }
    }

    sealed class UiEvent {
        data class ToastMessage(val message: String): UiEvent()
    }

}