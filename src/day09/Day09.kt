package day09

import utils.appliedTo
import utils.readInput
import java.util.List.copyOf
import kotlin.math.abs

enum class Move(val regex: Regex, val applyTo: (Pair<Int, Int>) -> Pair<Int, Int>) {
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
        repeat(step) { _ ->
            val previousHead = headPosition
            headPosition = move.applyTo(headPosition)
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


fun part2(input: List<String>): Int {

    val moves = input.map { Move.of(it) }

    println(moves)

// head, 1, 2, 3, 4, 5, 6, 7, 8, 9
    val knots = mutableListOf<Pair<Int, Int>>()

    repeat(10) { knots.add(0 to 0) }
    val tailVisitedPositions = mutableSetOf(knots[9])

    moves.forEach { (move, step): Pair<Move, Int> ->
        repeat(step) { _ ->
            val previous = copyOf(knots)

            knots[0] = move.applyTo(knots[0])
//            print("head = ${knots[0]}, ")
            for (i in 1..9) {
                fun Pair<Int, Int>.moveToward(headPosition: Pair<Int, Int>) =
                    if (isWithin1Of(headPosition)) this else previous[i - 1]

                knots[i] = knots[i].moveToward(knots[i - 1])
            }

            tailVisitedPositions.add(knots[9]/* .also { println("tail = $it") }*/)
        }
        for (i in 0..9)
            print("$i = ${knots[i]}, ")
        println()
    }
    println(tailVisitedPositions)

    return tailVisitedPositions.size
}


private fun Pair<Int, Int>.isWithin1Of(headPosition: Pair<Int, Int>) =
    abs(first - headPosition.first) <= 1 && abs(second - headPosition.second) <= 1

private fun incrementX(
    headPosition: Pair<Int, Int>
) = headPosition.first + 1 to headPosition.second

private fun decrementX(
    headPosition: Pair<Int, Int>
) = headPosition.first - 1 to headPosition.second

private fun decrementY(
    headPosition: Pair<Int, Int>
) = headPosition.first to headPosition.second - 1

private fun incrementY(
    headPosition: Pair<Int, Int>,
) = headPosition.first to headPosition.second + 1

fun main() {

    val testInput = readInput("input_test")
    val input = readInput("input")

    // part 1
//    ::part1.appliedTo(testInput, returns = 88)
//    println("Part 1: ${part1(input)}")

    // part 2
    ::part2.appliedTo(testInput, returns = 36)
    println("Part 2: ${part2(input)}")
}

