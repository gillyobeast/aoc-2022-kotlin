fun main() {
    fun maxTotalCalories(input: List<String>): Int {
        val elvesCalories = mutableListOf<Int>()
        var total = 0
        input.forEach{
            if (it.isBlank()){
                elvesCalories.add(total)
                total = 0
            }
            else
                total += it.toInt()
        }
        return elvesCalories.max()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(maxTotalCalories(testInput) == 24000) { "expected 24000 but was ${maxTotalCalories(testInput)}"}

    val input = readInput("Day01")
    println(maxTotalCalories(input))
    println(part2(input))
}
