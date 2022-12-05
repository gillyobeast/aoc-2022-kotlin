import java.util.*

fun main() {

    val whitespace = "\\s".toRegex()

    fun List<String>.extractIndices(): List<String> {
        return last().chunked(4).filter(String::isNotBlank)
    }

    fun parse(input: List<String>): Pair<List<String>, List<String>> = input
        .filter(String::isNotEmpty)
        .partition { it.startsWith("move") }

    fun String.extractLabel(): Char {
        return if (this.length > 1) this[1] else this[0]
    }

    fun String.stripSpaces(): String = replace(whitespace, "")

    fun buildStacks(stackData: List<String>): List<Stack<Char>> {
        val numberOfStacks = stackData.extractIndices().last().stripSpaces()

        val stacks = mutableListOf<Stack<Char>>()
        repeat(numberOfStacks.toInt()) {
            stacks.add(Stack())
        }

        stackData.dropLast(1).forEach {
            it.chunked(4).forEachIndexed { idx, item ->
                if (item.isNotBlank()) {
                    stacks[idx].push(item.extractLabel())
                }
            }
        }
        return stacks.onEach { it.reverse() }.toList()
    }


    fun String.applyTo(stacks: List<Stack<Char>>) {
        val split = this.split(whitespace)
        val count = split[1].toInt()
        val source = stacks[split[3].toInt() - 1]
        val target = stacks[split[5].toInt() - 1]

        repeat(count) {
            target.push(source.pop())
        }
    }

    fun List<String>.applyTo(stacks: List<Stack<Char>>) {
        forEach { it.applyTo(stacks) }
    }

    fun part1(input: List<String>): String {

        val (instructions, stackData) = parse(input)

        val stacks = buildStacks(stackData)
//        println("stacks = ${stacks}")

        instructions.applyTo(stacks)


        return stacks.map { it.pop() }.joinToString("")
    }

    fun part2(input: List<String>): String {
        return input.size.toString()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input_test")
    val input = readInput("input")

    // part 1
    ::part1.forInput(testInput, returns = "CMZ")
    println("Part 1: ${part1(input)}")

    // part 2
    ::part2.forInput(testInput, returns = 12)
    println("Part 2: ${part2(input)}")
}




