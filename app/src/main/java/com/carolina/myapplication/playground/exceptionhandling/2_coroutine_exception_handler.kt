package com.carolina.myapplication.playground.exceptionhandling // ktlint-disable filename

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun main() {
    val exceptionHandler = CoroutineExceptionHandler { context, exception ->
        println("Caught $exception in CoroutineExceptionHandler")
    }

    val scope = CoroutineScope(Job())

    scope.launch(exceptionHandler) {
        launch {
            functionThatThrowsIt()
        }
    }

    Thread.sleep(100)
}
