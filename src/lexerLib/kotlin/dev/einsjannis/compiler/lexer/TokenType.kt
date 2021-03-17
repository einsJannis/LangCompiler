package dev.einsjannis.compiler.lexer

import java.io.File

interface TokenType {

    val regex: Regex

    val name: String

    fun new(index: Int, content: String, file: File): Token?

}
