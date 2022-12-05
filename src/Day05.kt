import java.util.Stack

fun main() {

    fun List<String>.extractIndices() =
        last().split("\\s+".toRegex()).filter (String::isNotBlank)

    fun parse(input: List<String>): Pair<List<String>, List<String>> = input
        .filter(String::isNotEmpty)
        .partition { it.startsWith("move") }

    fun buildStacks(indices: List<String>): List<Stack<Char>> {
        val numberOfStacks = indices.last()

        val stacks = mutableListOf<Stack<Char>>()
        repeat(numberOfStacks.toInt()) {
            stacks.add(Stack())
        }
        return stacks.toList()
    }

    fun part1(input: List<String>): Int {

        val (instructions, stackData) = parse(input)

        println("stackData: \n${stackData.joinToString("\n")}")
        println("instructions: ${instructions}")

        val indices = stackData.extractIndices()
        println("indices = ${indices}")
        val stacks = buildStacks(indices)



        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input_test")
    val input = readInput("input")

    // part 1
    ::part1.forInput(testInput, returns = 15)
    println("Part 1: ${part1(input)}")

    // part 2
    ::part2.forInput(testInput, returns = 12)
    println("Part 2: ${part2(input)}")
}

