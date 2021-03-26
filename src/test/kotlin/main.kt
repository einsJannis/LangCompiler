package dev.einsjannis.lang.compiler

import java.io.File

fun main() {
    println("a")
    val file = File("testResources/code.lang")
    Compiler.compile(file, File("/"))
}
