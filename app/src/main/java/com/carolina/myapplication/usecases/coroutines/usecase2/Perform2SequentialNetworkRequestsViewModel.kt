package com.carolina.myapplication.usecases.coroutines.usecase2

import androidx.lifecycle.viewModelScope
import com.carolina.myapplication.base.BaseViewModel
import com.carolina.myapplication.mock.MockApi
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase2.mockApi
import kotlinx.coroutines.launch

class Perform2SequentialNetworkRequestsViewModel(
    private val mockApi: MockApi = mockApi(),
) : BaseViewModel<UiState>() {

    fun perform2SequentialNetworkRequest() {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val recentVersions = mockApi.getRecentAndroidVersions()
                val mostRecentVersion = recentVersions.last()

                val featuresOfMostRecentVersion =
                    mockApi.getAndroidVersionFeatures(mostRecentVersion.apiLevel)

                uiState.value = UiState.Success(featuresOfMostRecentVersion)
            } catch (exception: Exception) {
                uiState.value = UiState.Error("Network Request failed")
            }
        }
    }
}
