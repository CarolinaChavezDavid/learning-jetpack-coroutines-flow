package com.carolina.myapplication.usecases.coroutines.usecase2.rx

import com.carolina.myapplication.mock.VersionFeatures

sealed class UiState {
    object Loading : UiState()
    data class Success(
        val versionFeatures: VersionFeatures,
    ) : UiState()

    data class Error(val message: String) : UiState()
}
