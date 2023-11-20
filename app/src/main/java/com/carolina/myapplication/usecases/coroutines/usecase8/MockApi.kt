package com.carolina.myapplication.usecases.coroutines.usecase8

import com.carolina.myapplication.mock.createMockApi
import com.carolina.myapplication.mock.mockAndroidVersions
import com.carolina.myapplication.utils.MockNetworkInterceptor
import com.google.gson.Gson

fun mockApi() =
    createMockApi(
        MockNetworkInterceptor()
            .mock(
                "http://localhost/recent-android-versions",
                { Gson().toJson(mockAndroidVersions) },
                200,
                5000,
            ),
    )
