package day08

import utils.*

fun part1(input: List<String>): Int {
    return countVisible(matrixOf(input))
}

private fun countVisible(matrix: Matrix<Int>): Int {
    var visible = 0

    matrix.iterate { rowIndex, colIndex ->
        val row = matrix[rowIndex]
        val column = matrix.column(colIndex)
        val tree = row[colIndex]
        val (treesBefore, treesAfter) = row.beforeAndAfter(colIndex)
        val (treesAbove, treesBelow) = column.beforeAndAfter(rowIndex)
        if (!treesBefore.blocks(tree)
            || !treesAfter.blocks(tree)
            || !treesAbove.blocks(tree)
            || !treesBelow.blocks(tree)
        ) visible++
    }
    return visible
}

private fun <E> Matrix<E>.iterate(block: (Int, Int) -> Unit) {
    indices.forEach { rowIndex ->
        this[rowIndex].indices.forEach { colIndex ->
            block(
                rowIndex,
                colIndex
            )
        }
    }
}

private fun List<Int>.blocks(tree: Int) = any { it >= tree }

private fun matrixOf(input: List<String>) = input.map {
    it.split("")
        .filter(String::isNotBlank)
        .map(String::toInt)
        .toList()
}


fun part2(input: List<String>): Int {
    val matrix = matrixOf(input)

    // from each tree (iterate over row and column


    return matrix.size
}

fun main() {

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input_test")
    val input = readInput("input")

    // part 1
    ::part1.appliedTo(testInput, returns = 21)
    println("Part 1: ${part1(input).shouldNotBe(equalTo = 1071)}")


    // part 2
    ::part2.appliedTo(testInput, returns = 8)
    println("Part 2: ${part2(input)}")
}


