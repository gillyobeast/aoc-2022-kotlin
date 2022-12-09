package day09

import utils.appliedTo
import utils.readInput

enum class Move(val regex: Regex) {
    DOWN("D \\d+".toRegex()),
    UP("U \\d+".toRegex()),
    LEFT("L \\d+".toRegex()),
    RIGHT("R \\d+".toRegex());

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

    return input.size
}

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

