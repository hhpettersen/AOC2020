package com.example.aoc.Helper

import java.io.File

class Helper {
    companion object {
        //returns each new line in the txt-file as string in a list
        fun useLines(path: String): List<String> {
            val defPath = "C:\\Users\\pette\\StudioProjects\\OCMC\\AOC\\app\\src\\main\\java\\com\\example\\aoc\\Inputs\\"
            val lineList = mutableListOf<String>()
            File("$defPath$path").useLines { lines -> lines.forEach { lineList.add(it) }}
            return lineList.map { it }
        }
    }
}