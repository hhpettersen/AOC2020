package com.example.aoc

fun main() {
    val stepsQ1 = Pair(3, 1)

    val stepsQ2 = listOf(
        Pair(1, 1),
        Pair(3, 1),
        Pair(5, 1),
        Pair(7, 1),
        Pair(1, 2)
    )

    fun steps(direction: Pair<Int, Int>): Long {
        val input = dataDay3.split("\n").drop(2)
        var formattedInput = mutableListOf<String>()
        if (direction.second > 1) {
            input.forEachIndexed { index, s ->
                if (index % 2 != 0) formattedInput.add(s)

            }
        } else formattedInput = input as MutableList<String>

        var counter = 0

        formattedInput.forEachIndexed { index, path ->
            val rightSteps = direction.first * (index + 1)
            val pathMultiplier = rightSteps / path.length
            val pathMultiplied = if (pathMultiplier > 0) path.repeat(pathMultiplier + 1) else path
            if (pathMultiplied[rightSteps].toString() == "#") counter++
        }
        return counter.toLong()
    }

    fun reduceSteps(directions: List<Pair<Int, Int>>): Long {
        val resList = mutableListOf<Long>()
        directions.forEach {
            resList.add(steps(it))
        }
        return resList.reduce { acc, l -> acc * l }
    }

    println("Solution 1: ${steps(stepsQ1)}")
    println("Solution 2: ${reduceSteps(stepsQ2)}")
}
