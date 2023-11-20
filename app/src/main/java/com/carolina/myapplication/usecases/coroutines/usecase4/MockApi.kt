package com.carolina.myapplication.usecases.coroutines.usecase4

import com.carolina.myapplication.mock.createMockApi
import com.carolina.myapplication.mock.mockAndroidVersions
import com.carolina.myapplication.mock.mockVersionFeaturesAndroid10
import com.carolina.myapplication.mock.mockVersionFeaturesOreo
import com.carolina.myapplication.mock.mockVersionFeaturesPie
import com.carolina.myapplication.utils.MockNetworkInterceptor
import com.google.gson.Gson

fun mockApi() = createMockApi(
    MockNetworkInterceptor()
        .mock(
            "http://localhost/recent-android-versions",
            { Gson().toJson(mockAndroidVersions) },
            200,
            1000,
        )
        .mock(
            "http://localhost/android-version-features/27",
            { Gson().toJson(mockVersionFeaturesOreo) },
            200,
            1000,
        )
        .mock(
            "http://localhost/android-version-features/28",
            { Gson().toJson(mockVersionFeaturesPie) },
            200,
            1000,
        )
        .mock(
            "http://localhost/android-version-features/29",
            { Gson().toJson(mockVersionFeaturesAndroid10) },
            200,
            1000,
        ),
)
