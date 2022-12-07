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

private val root = Directory("/")

private val whitespace = "\\s".toRegex()

private val newline = "\\n".toRegex()


fun part1(input: List<String>): Int {
    val tree: FsObject = root
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
                    .filter(String::isNotBlank).forEach {
                        val response = it.split(whitespace)
                        if (response[0] == "dir") {
                            currentDirectory.addChild(Directory(response[1]))
                        } else { // file
                            val child = File(response[1], response[0].toInt())
                            currentDirectory.addChild(child)
                        }
                    }

            }
        }
    println(tree.toString())



    return tree.findDirectoriesSmallerThan(100_000)
        .sumOf { it.sizeOnDisk() }
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
    val part1 = part1(input)
    check(part1 != 2127288) { "Shouldn't be 2127288!" }
    println("Part 1: $part1")

    // part 2
    ::part2.appliedTo(testInput, returns = -1)
    println("Part 2: ${part2(input)}")
}

class File(name: String) : FsObject(name) {
    constructor(name: String, size: Int) : this(name) {
        this.size = size
    }

    override fun type(): String = "file"
}

class Directory(name: String) : FsObject(name) {
    override fun type(): String = "dir"

}


sealed class FsObject(private val name: String) {
    var parent: FsObject? = null

    val children: MutableList<FsObject> = mutableListOf()

    var size: Int = 0

    fun sizeOnDisk(): Int {
        val childFiles = getChildFiles()
        return childFiles.sumOf { it.size }
    }

    fun addChild(child: FsObject): FsObject {
        children.add(child)
        child.parent = this
        return child
    }

    private fun getChildFiles(): List<File> {
        if (this is File) return listOf(this)
        val (files, dirs) = children.partition { it is File }
        val children = mutableListOf<File>()
        children.addAll(files.map { it as File })
        children.addAll(dirs.flatMap { it.getChildFiles() })
        return children.toList()
    }

    private fun depth(): Int {
        var parent = this.parent
        var depth = 0
        while (parent != null) {
            depth++
            parent = parent.parent
        }
        return depth
    }

    override fun toString(): String {
        var name = "${"\t".repeat(depth())}- $name (size = ${sizeOnDisk()}, ${type()})"
        if (children.isNotEmpty()) {
            name += children.joinToString("") { "\n$it" }
        }
        return name
    }

    abstract fun type(): String

    fun getOrAddDir(name: String): FsObject {
        return children.find { it.name == name } ?: addChild(Directory(name))
    }

    fun findDirectoriesSmallerThan(upperLimit: Int): List<FsObject> {
        val directories = mutableListOf<FsObject>()

        if (sizeOnDisk() in 1..upperLimit && this is Directory) {
            directories.add(this)
        }
        children
            .filterIsInstance<Directory>()
            .forEach {
                directories.addAll(it.findDirectoriesSmallerThan(upperLimit))
            }


        return directories
    }
}
