package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase8

import com.carolina.myapplication.mock.AndroidVersion

sealed class UiState {
    sealed class Loading : UiState() {
        object LoadFromDb : Loading()
        object LoadFromNetwork : Loading()
    }

    data class Success(val dataSource: DataSource, val recentVersions: List<AndroidVersion>) :
        UiState()

    data class Error(val dataSource: DataSource, val message: String) : UiState()
}