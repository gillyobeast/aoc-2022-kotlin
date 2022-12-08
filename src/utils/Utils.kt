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


typealias Matrix<E> = List<List<E>>

fun Matrix<Int>.prettyPrint() {
    println(joinToString("\n"))
}

fun <T> T.shouldNotBe(equalTo: T): T {
    check(this != equalTo) { "Shouldn't be $equalTo" }
    return this
}

fun <E> Matrix<E>.transposed(): Matrix<E> {
    checkSquare()
    val copy = mutableListOf<List<E>>()
    for (i: Int in 0..this.lastIndex) {
        val list = mutableListOf<E>()
        for (j: Int in 0..this[i].lastIndex) {
            list.add(j, this[j][i])
        }
        copy.add(list.toList())
    }

    return copy.toList()
}

private fun <E> Matrix<E>.checkSquare() {
    forEach { check(it.size == this[0].size) { "Non-square matrix passed to transpose" } }
}


fun List<Int>.beforeAndAfter(index: Int): Pair<List<Int>, List<Int>> {
    return subList(0, index) to subList(index + 1, size)
}

fun <E> Matrix<E>.column(i: Int): List<E> {
    return map { it[i] }.toList()
}
