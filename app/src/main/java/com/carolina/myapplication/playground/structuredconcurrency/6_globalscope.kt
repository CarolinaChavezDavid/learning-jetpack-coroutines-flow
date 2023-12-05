package com.carolina.myapplication.playground.structuredconcurrency

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("Job of GlobalScope: ${GlobalScope.coroutineContext[Job]}")

    val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
    }
    val job = GlobalScope.launch(coroutineExceptionHandler) {
        val child = launch {
            delay(50)
            throw RuntimeException()
            println("Still running")
            delay(50)
            println("Still running")
            delay(50)
            println("Still running")
            delay(50)
            println("Still running")
        }
    }

    delay(100)

    job.cancel()

    delay(300)
}
