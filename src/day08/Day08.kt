package day08

import utils.appliedTo
import utils.readInput
import utils.shouldNotBe
import utils.transposed

fun part1(input: List<String>): Int {
    val matrix: List<List<Int>> = matrixOf(input)

    var visible = countVisible(matrix)
    println()
    visible += countVisible(matrix.transposed())

//    matrix.apply { prettyPrint() }
//        .transposed().prettyPrint()

    return visible
}

private fun countVisible(matrix: List<List<Int>>): Int {
    var visible = 0
    matrix.forEachIndexed { i, row ->
        row.forEachIndexed { j, value ->
//            println("row = ${row}")
//            println("idx = ${idx}")
            val before = row.subList(0, j)
            val after = row.subList(j + 1, row.size)
            print("${before} "); print("[$value]");println(" ${after}")
            if (before.all { it < value }
                || after.all { it < value }) visible++
        }
    }
    return visible
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
    ::part1.appliedTo(testInput, returns = 21)
    println("Part 1: ${part1(input).shouldNotBe(equalTo = 1071)}")


    // part 2
    ::part2.appliedTo(testInput, returns = -1)
    println("Part 2: ${part2(input)}")
}


