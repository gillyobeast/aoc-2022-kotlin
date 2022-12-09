package day09

import utils.appliedTo
import utils.readInput
import kotlin.math.abs
import kotlin.reflect.KFunction2

enum class Move(val regex: Regex, val apply: KFunction2<Pair<Int, Int>, Int, Pair<Int, Int>>) {
    UP("U \\d+".toRegex(), ::incrementY),
    DOWN("D \\d+".toRegex(), ::decrementY),
    LEFT("L \\d+".toRegex(), ::decrementX),
    RIGHT("R \\d+".toRegex(), ::incrementX);

    companion object {
        private val digits = "(\\d+)".toRegex()
        fun of(string: String): Pair<Move, Int> {
            val direction = Move.values().first { it.regex.matches(string) }
            return direction to digits.find(string)!!.value.toInt()
        }
    }
}

fun part1(input: List<String>): Int {

    val moves = input.map { Move.of(it) }

    println(moves)

    var headPosition = 0 to 0 // x to y
    var tailPosition = 0 to 0 // x to y
    val tailVisitedPositions = mutableSetOf(tailPosition)

    moves.forEach { (move, step): Pair<Move, Int> ->
        repeat(step) {
            val previousHead = headPosition
            headPosition = move.apply(headPosition, 1)
            print("head = ${headPosition} ")

            tailPosition =
                if (tailPosition.isWithin1Of(headPosition)) tailPosition
                else previousHead

            println("tail = ${tailPosition}")

            tailVisitedPositions.add(tailPosition)
        }
    }
    println(tailVisitedPositions)

    return tailVisitedPositions.size
}

private fun Pair<Int, Int>.isWithin1Of(headPosition: Pair<Int, Int>) =
    abs(first - headPosition.first) <= 1 && abs(second - headPosition.second) <= 1



private fun incrementX(
    headPosition: Pair<Int, Int>,
    step: Int
) = headPosition.first + step to headPosition.second

private fun decrementX(
    headPosition: Pair<Int, Int>,
    step: Int
) = headPosition.first - step to headPosition.second

private fun decrementY(
    headPosition: Pair<Int, Int>,
    step: Int
) = headPosition.first to headPosition.second - step

private fun incrementY(
    headPosition: Pair<Int, Int>,
    step: Int
) = headPosition.first to headPosition.second + step

fun part2(input: List<String>): Int {
    return input.size
}

fun main() {

    val testInput = readInput("input_test")
    val input = readInput("input")

    // part 1
    ::part1.appliedTo(testInput, returns = 13)
    println("Part 1: ${part1(input)}")

    // part 2
    ::part2.appliedTo(testInput, returns = -1)
    println("Part 2: ${part2(input)}")
}

