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

private val ROOT = FileOrDirectory("/", Directory)

private val whitespace = "\\s".toRegex()

val newline = "\\n".toRegex()

fun part1(input: List<String>): Int {
    val tree: FileOrDirectory = ROOT
    var currentDirectory: FileOrDirectory = tree

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
                        currentDirectory.parent ?: run {
                            println("Cannot go up from $currentDirectory with $it")
                            tree
                        }
                    } else {
                        currentDirectory[dirname, Directory]
                    }
            } else { // ls
                lines.drop(1) // ignore the ls
                    .filter(String::isNotBlank)
                    .forEach {
                        it.split(newline).forEach {
                            val response = it.split(whitespace)
                            if (response[0] == "dir") {
                                currentDirectory[response[1], Directory]
                            } else { // file
                                val child = currentDirectory.addChild(response[1], File)
                                child.size = response[0].toInt()
                            }
                        }
                    }
            }
        }
    println(tree.toString())



    return tree.size
}
//
//private fun <T> Sequence<T>.third(): T {
//    return drop(2).first()
//}
//
//private fun <T> Sequence<T>.second(): T {
//    return drop(1).first()
//}

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


sealed class Type
object File : Type() {
    override fun toString() = "file"
}

object Directory : Type() {
    override fun toString() = "dir"

}


class FileOrDirectory(private val name: String, private val type: Type) {
    var parent: FileOrDirectory? = null

    private val children: MutableList<FileOrDirectory> = mutableListOf()

    var size: Int = 0
        get() = if (field != 0) field else children.sumOf { it.size }

    fun addChild(child: FileOrDirectory): FileOrDirectory {
        children.add(child)
        child.parent = this
        return child
    }

    fun addChild(name: String, type: Type): FileOrDirectory {
        return addChild(FileOrDirectory(name, type))
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
        var name = "${"\t".repeat(depth())}- $name ($type, size = ${size})"
        if (!children.isEmpty()) {
            name += children.joinToString { "\n$it " }
        }
        return name
    }

    operator fun get(name: String, type: Type): FileOrDirectory {
        return children.find { it.name == name } ?: addChild(name, type)
    }
}
