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
        headPosition = move.apply(headPosition, step)

        tailPosition = tailPosition.moveToward(headPosition)

        tailVisitedPositions.add(tailPosition)
    }

    return tailVisitedPositions.size
}

private fun Pair<Int, Int>.moveToward(other: Pair<Int, Int>): Pair<Int, Int> {
    if (closeInXTo(other)) {
        return if (closeInYTo(other)) { // no need to move
            this
        } else { // in same x but need to move y towards other
            this.first to this.second.moveToward(other.second)
        }

    } else if (closeInYTo(other)) { // in same y but need to move y towards other
        return first.moveToward(other.first) to this.second
    }else
        return first.moveToward(other.first)


}

private fun Int.moveToward(other: Int) = this + distanceFrom(other)

fun Pair<Int, Int>.closeInYTo(headPosition: Pair<Int, Int>): Boolean =
    second.closeTo(headPosition.second)

private fun Pair<Int, Int>.closeInXTo(headPosition: Pair<Int, Int>): Boolean =
    first.closeTo(headPosition.first)

private fun Int.closeTo(other: Int): Boolean {
    return abs(distanceFrom(other)) <= 1
}

private fun Int.distanceFrom(other: Int) = other - this

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

