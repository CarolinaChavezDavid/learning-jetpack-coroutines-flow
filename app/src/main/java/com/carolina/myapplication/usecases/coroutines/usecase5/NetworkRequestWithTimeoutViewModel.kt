package com.carolina.myapplication.usecases.coroutines.usecase5

import androidx.lifecycle.viewModelScope
import com.carolina.myapplication.base.BaseViewModel
import com.carolina.myapplication.mock.MockApi
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase5.UiState
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase5.mockApi
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

class NetworkRequestWithTimeoutViewModel(
    private val api: MockApi = mockApi(),
) : BaseViewModel<UiState>() {

    fun performNetworkRequest(timeout: Long) {
        uiState.value = UiState.Loading
        // usingWithTimeout(timeout)
        usingWithTimeoutOrNull(timeout)
    }

    private fun usingWithTimeout(timeout: Long) {
        viewModelScope.launch {
            try {
                val recentVersions = withTimeout(timeout) {
                    api.getRecentAndroidVersions()
                }
                uiState.value = UiState.Success(recentVersions)
            } catch (timeoutCancellationException: TimeoutCancellationException) {
                uiState.value = UiState.Error("Network Request timed out!")
            } catch (exception: Exception) {
                uiState.value = UiState.Error("Network Request failed!")
            }
        }
    }

    private fun usingWithTimeoutOrNull(timeout: Long) {
        viewModelScope.launch {
            try {
                val recentVersions = withTimeoutOrNull(timeout) {
                    api.getRecentAndroidVersions()
                }

                if (recentVersions != null) {
                    uiState.value = UiState.Success(recentVersions)
                } else {
                    uiState.value = UiState.Error("Network Request timed out!")
                }
            } catch (exception: Exception) {
                uiState.value = UiState.Error("Network Request failed!")
            }
        }
    }
}
