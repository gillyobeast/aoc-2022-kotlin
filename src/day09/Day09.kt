package day09

import utils.appliedTo
import utils.readInput
import kotlin.math.abs

enum class Move(val regex: Regex, val applyTo: (Point) -> Point) {
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

typealias Point = Pair<Int, Int>

fun part1(input: List<String>): Int {

    val moves = input.map { Move.of(it) }

//    println(moves)

    var headPosition = 0 to 0 // x to y
    var tailPosition = 0 to 0 // x to y
    val tailVisitedPositions = mutableSetOf(tailPosition)

    moves.forEach { (move, step): Pair<Move, Int> ->
        repeat(step) { _ ->
            headPosition = move.applyTo(headPosition)
//            print("head = $headPosition ")

            tailPosition = tailPosition.moveDiagonallyTowards(headPosition)

//            println("tail = $tailPosition")

            tailVisitedPositions.add(tailPosition)
        }
    }
//    println(tailVisitedPositions)

    return tailVisitedPositions.size
}


fun part2(input: List<String>): Int {

    val moves = input.map { Move.of(it) }

//    println(moves)

// head, 1, 2, 3, 4, 5, 6, 7, 8, 9
    val knots = mutableListOf<Point>()

    repeat(10) { knots.add(0 to 0) }
    val tailVisitedPositions = mutableSetOf(knots[9])

    moves.forEach { (move, step): Pair<Move, Int> ->
        repeat(step) { _ ->

            knots[0] = move.applyTo(knots[0])
//            print("head = ${knots[0]}, ")
            for (i in 1..9) {

                knots[i] = knots[i].moveDiagonallyTowards(knots[i - 1])
//                print("; $i = ${knots[i]}, ")
            }
//            println()

            tailVisitedPositions.add(knots[9])
        }
//        for (i in 0..9)
//            print("$i = ${knots[i]}, ")
//        println()
    }
//    println(tailVisitedPositions)

    return tailVisitedPositions.size
}

private fun Point.moveDiagonallyTowards(other: Point): Point {
    this.log("start ")
    other.log(", towards ")
    return if (this.isWithin1Of(other)) this
    else {
        val x = first.moveToward(other.first)
        val y = second.moveToward(other.second)
        (x to y).log(", moving to ")
    }
}

private fun Int.moveToward(first1: Int) =
    if (this == first1) this else if (this < first1) this + 1 else this - 1


private fun Point.isWithin1Of(headPosition: Point): Boolean {
    return abs(first - headPosition.first).log(", x ") <= 1 && abs(second - headPosition.second).log(
        ", y "
    ) <= 1
}

private fun <T> T.log(s: String = ""): T = this//.also { print(s + it) }

private fun incrementX(
    headPosition: Point
) = headPosition.first + 1 to headPosition.second

private fun decrementX(
    headPosition: Point
) = headPosition.first - 1 to headPosition.second

private fun decrementY(
    headPosition: Point
) = headPosition.first to headPosition.second - 1

private fun incrementY(
    headPosition: Point,
) = headPosition.first to headPosition.second + 1

fun main() {

    val testInput = readInput("input_test")
    val input = readInput("input")

    // part 1
    ::part1.appliedTo(testInput, returns = 88)
    println("Part 1: ${part1(input)}")

    // part 2
    ::part2.appliedTo(testInput, returns = 36)
    println("Part 2: ${part2(input)}")
}

