package com.carolina.myapplication.usecases.flow.usecase3

import android.content.Context
import com.carolina.myapplication.usecases.flow.mock.createFlowMockApi
import com.carolina.myapplication.usecases.flow.mock.fakeCurrentStockPrices
import com.carolina.myapplication.utils.MockNetworkInterceptor
import com.google.gson.Gson

fun mockApi(context: Context) =
    createFlowMockApi(
        MockNetworkInterceptor()
            .mock(
                path = "/current-stock-prices",
                body = { Gson().toJson(fakeCurrentStockPrices(context)) },
                status = 200,
                delayInMs = 1500,
                errorFrequencyInPercent = 50,
            ),
    )
