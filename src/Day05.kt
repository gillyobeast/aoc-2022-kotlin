fun main() {


    fun part1(input: List<String>): Int {
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

