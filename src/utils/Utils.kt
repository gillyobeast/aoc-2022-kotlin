package utils

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()

/**
 * Converts string to utils.md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun <L, T> ((L) -> T).appliedTo(
    input: L,
    returns: T
) {
    val output = this(input)
    check(output == returns) { "expected $returns but was $output" }
}

fun List<List<Int>>.prettyPrint() {
    println(joinToString("\n"))
}

fun <T> T.shouldNotBe(equalTo: T): T {
    check(this != equalTo) { "Shouldn't be $equalTo" }
    return this
}

fun List<List<Int>>.transposed(): List<List<Int>> {
    checkSquare()
    val copy = mutableListOf<MutableList<Int>>()
    for (i: Int in 0..this.lastIndex) {
        val list = mutableListOf<Int>()
        for (j: Int in 0..this[i].lastIndex) {
            list.add(j, this[j][i])
        }
        copy.add(list)
    }

    return copy
}

private fun List<List<Int>>.checkSquare() {
    forEach { check(it.size == this[0].size) { "Non-square matrix passed to transpose" } }
}


fun List<Int>.beforeAndAfter(index: Int): Pair<List<Int>, List<Int>> {
    return subList(0, index) to subList(index + 1, size)
}

fun List<List<Int>>.column(i: Int): List<Int> {
    return map { it[i] }.toList()
}
