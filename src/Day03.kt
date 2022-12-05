fun main() {

    val lowerPriorities =
        ("abcdefghijklmnopqrstuvwxyz".toCharArray()).zip((1..26).toList()).toMap()
    val upperPriorities =
        ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()).zip((27..52).toList()).toMap()

    fun splitBackpack(backpack: String): Pair<String, String> {
        val n = backpack.length / 2
        return backpack.substring(0, n) to backpack.substring(n, backpack.length)
    }

    fun findCommonItem(backPack: Pair<String, String>): Char {
        for (i in backPack.first) {
            if (backPack.second.contains(i.toString().toRegex())) {
                return i
            }
        }
        throw IllegalStateException("No common character found between halves of [$backPack]!")
    }

    fun toPriority(c: Char): Int {
        return lowerPriorities[c] ?: upperPriorities[c]
        ?: throw IllegalStateException("No priority found for [$c]!")
    }

    fun part1(input: List<String>): Int {
        return input
            .map(::splitBackpack)
            .map(::findCommonItem)
            .map(::toPriority)
            .sum()

    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input_test")
    val input = readInput("input")

    // part 1
    ::part1.forInput(testInput, returns = 157)
    println("Part 1: ${part1(input)}")

    // part 2
    ::part2.forInput(testInput, returns = 1)
    println("Part 2: ${part2(input)}")
}

