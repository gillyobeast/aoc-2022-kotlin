package day07

import appliedTo
import readInput


/// strategy:
//  add `/` to tree
//  dirname = "/"
//  split input by lines starting $
//  for each of those:
//      if `cd ..`, set dirname to 'parent'
//      if `cd dirname` set `currentDir` to `dirname`
//      if `ls`, iterate over remaining lines:
//          if starts with `\d+`, add child with that size and name that follows
//          else if starts with `dir dirname`, add child with name `dirname`

//  then traverse the tree, grabbing all `dir`s with size < limit and adding to list:
//  list.sumOf { it.size }

fun part1(input: List<String>): Int {
    return input.size
}

fun part2(input: List<String>): Int {
    return input.size
}

fun main() {

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input_test")
    val input = readInput("input")

    // part 1
    ::part1.appliedTo(testInput, returns = 95437)
    println("Part 1: ${part1(input)}")

    // part 2
    ::part2.appliedTo(testInput, returns = -1)
    println("Part 2: ${part2(input)}")
}

