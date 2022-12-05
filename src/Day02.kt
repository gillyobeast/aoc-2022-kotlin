fun main() {

    fun totalRpsScore(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    val input = readInput("Day02")

    // part 1
    ::totalRpsScore.forInput(testInput, returns = 16)
    println("Part 1: ${totalRpsScore(input)}")

    // part 2
    ::part2.forInput(testInput, returns = 1)
    println("Part 2: ${part2(input)}")
}

