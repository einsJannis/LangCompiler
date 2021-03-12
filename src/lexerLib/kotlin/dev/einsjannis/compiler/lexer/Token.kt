package dev.einsjannis.compiler.lexer

import java.io.File

interface Token {

    val type: TokenType

    val index: Int

    val content: String

    val file: File

}
