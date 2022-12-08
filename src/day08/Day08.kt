package day08

import utils.appliedTo
import utils.readInput

fun part1(input: List<String>): Int {
    val matrix: List<List<Int>> = matrixOf(input)


//    matrix.apply { prettyPrint() }
//        .transposed().prettyPrint()

    return matrix[0].size
}

private fun matrixOf(input: List<String>) = input.map {
    it.split("")
        .filter(String::isNotBlank)
        .map(String::toInt)
        .toList()
}


fun part2(input: List<String>): Int {
    return input.size
}

fun main() {

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input_test")
    val input = readInput("input")

    // part 1
    ::part1.appliedTo(testInput, returns = 5)
    println("Part 1: ${part1(input)}")

    // part 2
    ::part2.appliedTo(testInput, returns = -1)
    println("Part 2: ${part2(input)}")
}

