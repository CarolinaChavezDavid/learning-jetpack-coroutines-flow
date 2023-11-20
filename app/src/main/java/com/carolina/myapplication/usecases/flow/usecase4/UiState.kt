package com.carolina.myapplication.usecases.flow.usecase4

import com.carolina.myapplication.usecases.flow.mock.Stock

sealed class UiState {
    object Loading : UiState()
    data class Success(val stockList: List<Stock>) : UiState()
    data class Error(val message: String) : UiState()
}
