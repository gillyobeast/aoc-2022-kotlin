private fun String.hasNoDuplicates(): Boolean {
    return this
        .groupBy { it }
        .all { it.value.size == 1 }
}

fun main() {


    fun findIndexOfUniqueSubstring(ofLength: Int, input: String): Int {
        input.windowed(ofLength)
            .forEachIndexed { idx, substring ->
                if (substring.length == ofLength && substring.hasNoDuplicates()) {
                    return idx + ofLength
                }
            }

        throw Exception("No run of $ofLength distinct chars found.")
    }

    fun part1(input: String): Int {

        return findIndexOfUniqueSubstring(ofLength = 4, input)
    }

    fun part2(input: String): Int {
        return findIndexOfUniqueSubstring(ofLength = 14, input)
    }

// test if implementation meets criteria from the description, like:
//    val testInput = readInput("input_test")
    val input = readInput("input")

// part 1
    ::part1.appliedTo("mjqjpqmgbljsphdztnvjfqwrcgsmlb", returns = 7)
    ::part1.appliedTo("bvwbjplbgvbhsrlpgdmjqwftvncz", returns = 5)
    ::part1.appliedTo("nppdvjthqldpwncqszvftbrmjlhg", returns = 6)
    ::part1.appliedTo("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", returns = 10)
    ::part1.appliedTo("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", returns = 11)
    println("Part 1: ${part1(input.first())}")

// part 2
    ::part2.appliedTo("mjqjpqmgbljsphdztnvjfqwrcgsmlb", returns = 19)
    ::part2.appliedTo("bvwbjplbgvbhsrlpgdmjqwftvncz", returns = 23)
    ::part2.appliedTo("nppdvjthqldpwncqszvftbrmjlhg", returns = 23)
    ::part2.appliedTo("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", returns = 29)
    ::part2.appliedTo("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", returns = 26)
    println("Part 2: ${part2(input.first())}")
}

