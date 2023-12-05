package com.carolina.myapplication.usecases.coroutines.usecase1

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    coroutinesExample()
}

fun coroutinesExample() = runBlocking {
    val job = launch {
        async(Dispatchers.IO) {
            delay(3000)
            println("caroutines 1")
        }.await()
        async(Dispatchers.Default) {
            delay(2000)
            println("caroutines 2")
        }.await()
        println("caroutines 3")
    }

    println("Couritines End")

    job.join()
    println("Finish")
}
