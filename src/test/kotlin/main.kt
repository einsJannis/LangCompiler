package dev.einsjannis.lang.compiler

import dev.einsjannis.compiler.parser.printTree
import dev.einsjannis.lang.compiler.parser.parse
import java.io.File

fun main() {
    println("a")
    val file = File("testResources/code.lang")
    val tree = parse(file)
    tree.printTree()
}
