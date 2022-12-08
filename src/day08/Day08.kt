package day08

import utils.appliedTo
import utils.readInput
import utils.shouldNotBe

fun part1(input: List<String>): Int {
    val matrix: List<List<Int>> = matrixOf(input)

    return countVisible(matrix)
}

private fun countVisible(matrix: List<List<Int>>): Int {
    var visible = 0
    matrix.forEachIndexed { i, row ->
        row.forEachIndexed { j, tree ->
            val column = matrix.column(j)
            val treesBefore = row.subList(0, j)
            val treesAfter = row.subList(j + 1, row.size)
            val treesAbove = column.subList(0, i)
            val treesBelow = column.subList(i + 1, column.size)
            print("${treesAbove.ifEmpty { "" }} [$tree] ${treesBelow.ifEmpty { "" }}\n")

            if (!treesBefore.blocks(tree)
                || !treesAfter.blocks(tree)
                || !treesAbove.blocks(tree)
                || !treesBelow.blocks(tree)
            ) visible++
        }
    }
    return visible
}

private fun List<List<Int>>.column(i: Int): List<Int> {
    return map { it[i] }.toList()
}

private fun List<Int>.blocks(tree: Int) = any { it >= tree }

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


