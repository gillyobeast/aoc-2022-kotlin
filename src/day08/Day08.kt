package day08

import utils.*
import kotlin.math.max

fun part1(input: List<String>): Int {
    return countVisible(matrixOf(input))
}

private fun countVisible(matrix: Matrix<Int>): Int {
    var visible = 0

    matrix.iterate { rowIndex, colIndex ->
        val (row, column, tree) = matrix[rowIndex, colIndex]
        val (treesBefore, treesAfter) = row.beforeAndAfter(colIndex)
        val (treesAbove, treesBelow) = column.beforeAndAfter(rowIndex)
        if (!treesBefore.blocks(tree) || !treesAfter.blocks(tree) || !treesAbove.blocks(tree) || !treesBelow.blocks(
                tree
            )
        ) visible++
    }
    return visible
}


private fun <E> Matrix<E>.iterate(block: (Int, Int) -> Unit) {
    indices.forEach { rowIndex ->
        this[rowIndex].indices.forEach { colIndex ->
            block(
                rowIndex, colIndex
            )
        }
    }
}

private fun List<Int>.blocks(tree: Int) = any { it >= tree }

private fun matrixOf(input: List<String>) = input.map {
    it.split("").filter(String::isNotBlank).map(String::toInt).toList()
}


fun part2(input: List<String>): Int {
    val matrix = matrixOf(input)

    var maxScenicScore = 0
    var maxTree = 0 to 0

    // from each tree, calculate view distance in each direction
    // multiply to get scenicScore
    // take max of max,
    matrix.iterate { rowIndex, colIndex ->
        val (row, column, tree) = matrix[rowIndex, colIndex]
        val hori = tree.viewDistanceInDirection(row, colIndex)
        val vert = tree.viewDistanceInDirection(column, rowIndex)
        val scenicScore = hori * vert
        maxScenicScore = max(scenicScore, maxScenicScore)
        maxTree = rowIndex to colIndex
    }

    println("maxTree = $maxTree")
    return maxScenicScore
}

private fun Int.viewDistanceInDirection(
    column: List<Int>,
    rowIndex: Int
): Int {
    val direction = column.beforeAndAfter(
        rowIndex
    )
    val viewDistanceBefore = viewDistanceTo(direction.first.asReversed())
    val viewDistanceAfter = viewDistanceTo(direction.second)
    return viewDistanceBefore * viewDistanceAfter
}

private fun Int.viewDistanceTo(ints: List<Int>) = ints.takeWhile { it < this }.size

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


