package com.example.aoc

import com.example.aoc.helper.Helper

fun main() {
    val parsed = Helper.useLines("day5.txt")
    val rowPart = parsed.map {
        it.substring(0, 7)
    }
    val columnPart = parsed.map {
        it.substring(7, it.length)
    }

    fun decode(rowCode: String, inputRange: IntRange, charDown: Char, charUp: Char): Int {
        var range = inputRange
        var decodedResult = 0
        rowCode.forEachIndexed { index, it ->
            when (it) {
                charDown -> {

                    val newFirst = range.first
                    val newLast = range.last - (range.last - range.first) / 2

                    range = newFirst..newLast

                    if (index == rowCode.length - 1) {
                        decodedResult = range.first
                    }
                }
                charUp -> {
                    val newFirst = (range.last - range.first) / 2 + range.first
                    val newLast = range.last

                    range = newFirst..newLast

                    if (index == rowCode.length - 1) {
                        decodedResult = range.last - 1
                    }
                }
            }
        }
        return decodedResult
    }

    val rowSum = rowPart.map {
        decode(it, 0..128, 'F', 'B') * 8
    }

    val colSum = columnPart.map {
        decode(it, 0..8, 'L', 'R')
    }

    val sum = rowSum.mapIndexed { index, row ->
        row + colSum[index]
    }

    val sumSort = sum.sorted()

    var sol2 = 0

    sumSort.forEachIndexed { index, i ->
        if (index != 0) {
            val s = i - sumSort[index - 1]
            if (s != 1) sol2 = i - 1
        }
    }

    println("Solution 1: ${sum.max()}")
    println("Solution 2: $sol2")
}