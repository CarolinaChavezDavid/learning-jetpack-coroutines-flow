package com.carolina.myapplication.usecases.coroutines.usecase7 // ktlint-disable filename

fun main() {
    var A = intArrayOf(-1, -3)
    solution(A)
    print(solution(A))
}

fun solution(A: IntArray): Int {
    val sorted = A.sorted().toSortedSet()

    val list = (sorted.first()..sorted.last().plus(1)).toList() - sorted

    var filter = list.filter { it > 0 }
    println(filter)
    return if (filter.isNotEmpty()) {
        filter.first()
    } else {
        1
    }
}
