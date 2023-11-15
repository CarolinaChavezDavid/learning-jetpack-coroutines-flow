package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase13

import com.carolina.myapplication.mock.VersionFeatures

sealed class UiState {
    object Loading : UiState()
    data class Success(
        val versionFeatures: List<VersionFeatures>
    ) : UiState()

    data class Error(val message: String) : UiState()
}