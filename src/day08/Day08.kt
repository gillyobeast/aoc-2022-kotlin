package day08

import utils.*

fun part1(input: List<String>): Int {
    return countVisible(matrixOf(input))
}

private fun countVisible(matrix: List<List<Int>>): Int {
    var visible = 0
    matrix.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { colIndex, tree ->
            val column = matrix.column(colIndex)
            val (treesBefore, treesAfter) = row.beforeAndAfter(colIndex)
            val (treesAbove, treesBelow) = column.beforeAndAfter(rowIndex)
            if (!treesBefore.blocks(tree)
                || !treesAfter.blocks(tree)
                || !treesAbove.blocks(tree)
                || !treesBelow.blocks(tree)
            ) visible++
        }
    }
    return visible
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


