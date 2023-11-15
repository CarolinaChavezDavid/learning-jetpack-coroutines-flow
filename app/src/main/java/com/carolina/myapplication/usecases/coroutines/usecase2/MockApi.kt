package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase2

import com.google.gson.Gson
import com.carolina.myapplication.mock.createMockApi
import com.carolina.myapplication.mock.mockAndroidVersions
import com.carolina.myapplication.mock.mockVersionFeaturesAndroid10
import com.carolina.myapplication.utils.MockNetworkInterceptor

fun mockApi() = createMockApi(
    MockNetworkInterceptor()
        .mock(
            "http://localhost/recent-android-versions",
            { Gson().toJson(mockAndroidVersions) },
            200,
            1500
        )
        .mock(
            "http://localhost/android-version-features/29",
            { Gson().toJson(mockVersionFeaturesAndroid10) },
            200,
            1500
        )
)