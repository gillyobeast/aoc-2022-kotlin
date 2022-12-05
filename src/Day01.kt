fun main() {
    fun maxTotalCalories(input: List<String>): Int {
        val elvesCalories = mutableListOf<Int>()
        var total = 0
        input.forEach {
            if (it.isBlank()) {
                elvesCalories.add(total)
                total = 0
            } else {
                total += it.toInt()
            }
        }
        return elvesCalories.max()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    ::maxTotalCalories.forInput(testInput, returns = 24000)
    ::part2.forInput(testInput, returns = 45000)

    val input = readInput("Day01")
    println(maxTotalCalories(input))
    println(part2(input))
}

