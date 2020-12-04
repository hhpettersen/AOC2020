package com.example.aoc

fun main() {

    fun parseInput(input: String): List<String> {
        val splitInput = input.split("")
        val parsed = mutableListOf<String>()
        var latestIndex = 0
        splitInput.forEachIndexed { index, s ->
            if (s == "\n" && splitInput[index - 1] == "\n") {
                val field = splitInput.subList(latestIndex, index)
                    .joinToString("")
                    .trim()
                    .replace("\n", " ")
                parsed.add(field)
                latestIndex = index
            }
            if (index == splitInput.size - 1) {
                parsed.add(
                    splitInput.subList(latestIndex, index)
                        .joinToString("")
                        .trim()
                        .replace("\n", " ")
                )
            }
        }
        return parsed
    }

    fun containsAllValidFields(string: String): Boolean {
        val req = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
        val sum = req.sumBy {
            if (string.contains(it)) 1 else 0
        }
        return sum == 7
    }

    fun solutionOne(input: String): Int {
        return parseInput(input).sumBy {
            if(containsAllValidFields(it)) 1 else 0
        }
    }

    fun solutionTwo(input: String): Int {
        val parsed = parseInput(input)
        var count = 0
        parsed.forEach { field ->
            if (containsAllValidFields(field)) {
                var validFields = 0
                field.split(" ").forEach { split ->
                    when (split.substring(0, 3)) {
                        "byr" -> if (split.filter { it.isDigit() }.toInt() in 1920..2002) validFields++
                        "iyr" -> if (split.filter { it.isDigit() }.toInt() in 2010..2020) validFields++
                        "eyr" -> if (split.filter { it.isDigit() }.toInt() in 2020..2030) validFields++
                        "hgt" -> {
                            val s = split.substring(4, split.length)
                            if (s.contains("cm")) {
                                if (s.filter { it.isDigit() }.toInt() in 150..193) validFields++
                            } else if (s.contains("in")) {
                                if (s.filter { it.isDigit() }.toInt() in 59..76) validFields++
                            }
                        }
                        "hcl" -> if (split.contains("#") && split.length == 11) validFields++
                        "ecl" -> {
                            val valid = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
                            valid.forEach {
                                if (split.contains(it)) validFields++
                            }
                        }
                        "pid" -> if (split.length == 13) validFields++
                    }
                }
                if (validFields == 7) count++
            }
        }
        return count
    }

    println("Solution 1: ${solutionOne(dataDay4)}")
    println("Solution 2: ${solutionTwo(dataDay4)}")
}

val testInput = """ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm

iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929

hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm

hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in"""

val validInput = """pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
hcl:#623a2f

eyr:2029 ecl:blu cid:129 byr:1989
iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm

hcl:#888785
hgt:164cm byr:2001 iyr:2015 cid:88
pid:545766238 ecl:hzl
eyr:2022

iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719"""