package dev.einsjannis.compiler.lexer

import java.io.File

fun token(regex: Regex): TokenType = object : TokenType {

    override val regex: Regex = regex

    override fun new(index: Int, content: String, file: File): Token {
        val type = this
        return object : Token {

            override val type: TokenType = type

            override val index: Int = index

            override val content: String = content

            override val file: File = file

        }
    }

}
