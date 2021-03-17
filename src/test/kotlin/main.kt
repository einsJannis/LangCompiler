package dev.einsjannis.lang.compiler

import dev.einsjannis.compiler.lexer.serialized
import java.io.File

fun main() {
    println("a")
    val file = File("testResources/code.lang")
    val tokens = lex(file)
    println(tokens.size)
    tokens.forEach { println(it.serialized) }
}
