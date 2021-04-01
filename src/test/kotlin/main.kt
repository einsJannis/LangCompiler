package dev.einsjannis.lang.compiler

import java.io.File

fun main() {
    val file = File("testResources/code.lang")
    Compiler.compile(file, File("/"))
}
