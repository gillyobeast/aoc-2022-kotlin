enum class Shape(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3), ;
}


fun String.toShape(): Shape {
    return if ("X" == this || "A" == this) Shape.ROCK else if ("Y" == this || "B" == this) Shape.PAPER else Shape.SCISSORS
}

private fun youWin(you: Shape, opponent: Shape) =
    ((you == Shape.ROCK && opponent == Shape.SCISSORS)
            || (you == Shape.PAPER && opponent == Shape.ROCK)
            || (you == Shape.SCISSORS && opponent == Shape.PAPER))

fun main() {


    fun outcomeScore(you: Shape, opponent: Shape): Int {
        if (you == opponent) return 3
        return if (youWin(you, opponent)) 6 else 0
    }

    fun toMap(input: List<String>): List<Pair<Shape, Shape>> =
        input.map { val parts = it.split(' '); parts[0].toShape() to parts[1].toShape() }

    fun totalRpsScore(input: List<String>): Int {
        val pairs = toMap(input)
        var score = 0
        for ((opponent, you) in pairs) {
            println("$opponent - $you")
            val shapeScore = you.score
            val outcomeScore = outcomeScore(you, opponent)
            val thisScore = shapeScore + outcomeScore
//            println("shapeScore = ${shapeScore}")
//            println("outcomeScore = ${outcomeScore}")
//            println("thisScore = ${thisScore}")
//            println()
            score += thisScore
        }

        return score
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    val input = readInput("Day02")

    // part 1
    ::totalRpsScore.forInput(testInput, returns = 15)
    println("Part 1: ${totalRpsScore(input)}")

    // part 2
    ::part2.forInput(testInput, returns = 1)
    println("Part 2: ${part2(input)}")
}

