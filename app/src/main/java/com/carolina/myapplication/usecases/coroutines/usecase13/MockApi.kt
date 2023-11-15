package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase13

import com.carolina.myapplication.mock.createMockApi
import com.carolina.myapplication.mock.mockAndroidVersions
import com.carolina.myapplication.mock.mockVersionFeaturesAndroid10
import com.carolina.myapplication.mock.mockVersionFeaturesOreo
import com.carolina.myapplication.mock.mockVersionFeaturesPie
import com.google.gson.Gson
import com.carolina.myapplication.utils.MockNetworkInterceptor

fun mockApi() = createMockApi(
    MockNetworkInterceptor()
        .mock(
            "http://localhost/recent-android-versions",
            { Gson().toJson(mockAndroidVersions) },
            200,
            1000
        )
        .mock(
            "http://localhost/android-version-features/27",
            { Gson().toJson(mockVersionFeaturesOreo) },
            MockNetworkInterceptor.INTERNAL_SERVER_ERROR_HTTP_CODE,
            100
        )
        .mock(
            "http://localhost/android-version-features/28",
            { Gson().toJson(mockVersionFeaturesPie) },
            200,
            1000
        )
        .mock(
            "http://localhost/android-version-features/29",
            { Gson().toJson(mockVersionFeaturesAndroid10) },
            200,
            1000
        )
)