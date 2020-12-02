package com.example.aoc

/*
https://adventofcode.com/2020/day/2
 */

fun main() {
    //PARSING
    val min = mutableListOf<Int>()
    val max = mutableListOf<Int>()
    val stringKey = mutableListOf<String>()
    val password = mutableListOf<String>()
    dataDay2.split(" ").forEachIndexed { index, s ->
        when (index % 3) {
            0 -> {
                val split = s.split("-")
                min.add(split[0].toInt())
                max.add(split[1].toInt())
            }
            1 -> stringKey.add(s.dropLast(1))
            2 -> {
                password.add(s)
            }
        }
    }

    //SOLUTION
    var validPasswordCountOne = 0
    var validPasswordCountTwo = 0
    password.forEachIndexed { index, pw ->
        val charKey = stringKey[index]
        //Q1
        val sum = pw.sumBy {
            if (charKey.contains(it)) 1 else 0
        }
        if (sum in min[index]..max[index]) validPasswordCountOne++
        //Q2
        if ((pw[min[index] - 1] == charKey.first()) != (pw[max[index] - 1] == charKey.first())) validPasswordCountTwo++
    }
    println("Q1: $validPasswordCountOne")
    println("Q2: $validPasswordCountTwo")
}

