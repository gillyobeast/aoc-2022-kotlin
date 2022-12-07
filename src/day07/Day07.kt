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

//  then traverse the tree, grabbing all `dir`s with size < limit and adding to list
//  list.sumOf { it.size }

private val ROOT = Directory("/")

private val whitespace = "\\s".toRegex()

val newline = "\\n".toRegex()

fun findDirectoriesSmallerThan(upperLimit: Int, inDirectory: FsObject): List<FsObject> {
    val directories = mutableListOf<FsObject>()

    return directories
}

fun part1(input: List<String>): Int {
    val tree: FsObject = ROOT
    var currentDirectory: FsObject = tree

    input.joinToString("\n")
        .split("\\\$".toRegex())
//        .also(::println)
        .drop(2) // as we're already in root
        .forEach { it ->
            val lines = it.split(newline)
            val argLine = lines.first()
            val args = argLine.split(whitespace)
            if (args[1] == "cd") {
                val dirname = args[2]
                currentDirectory =
                    if (dirname == "..") {
                        currentDirectory.parent!!
                    } else {
                        currentDirectory.getOrAddDir(dirname)
                    }
            } else { // ls
                lines.drop(1) // ignore the ls
                    .filter(String::isNotBlank)
                    .forEach {
                        it.split(newline).forEach {
                            val response = it.split(whitespace)
                            if (response[0] == "dir") {
                                currentDirectory.addChild(Directory(response[0]))
                            } else { // file
                                val child = currentDirectory.addChild(File(response[1]))
                                child.size = response[0].toInt()
                            }
                        }
                    }
            }
        }
    println(tree.toString())



    return findDirectoriesSmallerThan(100_000, inDirectory = tree)
        .sumOf { it.size }
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

class File(name: String) : FsObject(name) {
    override fun type(): String = "file"
}

class Directory(name: String) : FsObject(name) {
    override fun type(): String = "dir"

}


abstract class FsObject(private val name: String) {
    var parent: FsObject? = null

    val children: MutableList<FsObject> = mutableListOf()

    var size: Int = 0
        get() = if (field != 0) field else children.sumOf { it.size }

    fun addChild(child: FsObject): FsObject {
        children.add(child)
        child.parent = this
        return child
    }

    fun depth(): Int {
        var parent = this.parent
        var depth = 0
        while (parent != null) {
            depth++
            parent = parent.parent
        }
        return depth
    }

    override fun toString(): String {
        var name = "${"\t".repeat(depth())}- $name (size = ${size}, ${type()})"
        if (children.isNotEmpty()) {
            name += children.joinToString("") { "\n$it" }
        }
        return name
    }

    abstract fun type(): String

    fun getOrAddDir(name: String): FsObject {
        return children.find { it.name == name } ?: addChild(Directory(name))
    }
}
