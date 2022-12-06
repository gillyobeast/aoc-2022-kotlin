fun main() {


    fun part1(input: String): Int {
        return input.length
    }

    fun part2(input: String): Int {
        return input.length
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
    ::part2.appliedTo("testInput", returns = -1)
    println("Part 2: ${part2(input.first())}")
}

