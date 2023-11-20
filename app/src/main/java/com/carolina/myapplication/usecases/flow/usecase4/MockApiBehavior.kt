package com.carolina.myapplication.usecases.flow.usecase4

import android.content.Context
import com.carolina.myapplication.utils.MockNetworkInterceptor
import com.google.gson.Gson
import com.carolina.myapplication.usecases.flow.mock.createFlowMockApi
import com.carolina.myapplication.usecases.flow.mock.fakeCurrentStockPrices

fun mockApi(context: Context) =
    createFlowMockApi(
        MockNetworkInterceptor()
            .mock(
                path = "/current-stock-prices",
                body = { Gson().toJson(fakeCurrentStockPrices(context)) },
                status = 200,
                delayInMs = 1500,
            ),
    )
