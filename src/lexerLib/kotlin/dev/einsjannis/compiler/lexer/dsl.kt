package dev.einsjannis.compiler.lexer

import java.io.File

class TokenListBuilder {

    private val backing = mutableListOf<TokenType>()

    fun build() = backing.toList()

    fun token(regex: Regex): TokenType {
        val result = object : TokenType {

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
        backing.add(result)
        return result
    }

}

fun tokens(block: TokenListBuilder.() -> Unit): List<TokenType> =
    TokenListBuilder().apply(block).build()

private val tokenListBuilder = TokenListBuilder()

val tokens: List<TokenType> get() = tokenListBuilder.build()

fun token(regex: Regex): TokenType = tokenListBuilder.token(regex)
