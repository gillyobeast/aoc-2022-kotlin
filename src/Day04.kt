fun main() {


    fun toPair(it: String, delimiter: String): Pair<String, String> {
        val halves = it.split(delimiter)
        return halves[0] to halves[1]
    }

    fun Pair<String, String>.toRange(): IntRange = first.toInt()..second.toInt()

    fun String.toRange(): IntRange = toPair(this, "-").toRange()


    fun parsePairs(input: List<String>) = input
        .map { toPair(it, ",") }
        .map { it.first.toRange() to it.second.toRange() }
        .toList()

    infix fun IntRange.fullyContains(other: IntRange) =
        this.all { it in other }

    fun Pair<IntRange, IntRange>.overlaps(): Boolean {
        val firstInSecond = second fullyContains first
        val secondInFirst = first fullyContains second
        return (firstInSecond or secondInFirst).also(::println)
    }


    fun part1(input: List<String>): Int {
        val (overlaps, _) = parsePairs(input)
            .partition { it.overlaps() }

        return overlaps.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input_test")
    val input = readInput("input")

    // part 1
    ::part1.forInput(testInput, returns = 2)
    println("Part 1: ${part1(input)}")

    // part 2
    ::part2.forInput(testInput, returns = 1)
    println("Part 2: ${part2(input)}")
}



