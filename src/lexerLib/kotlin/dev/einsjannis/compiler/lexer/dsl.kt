@file:Suppress("UNUSED")

package dev.einsjannis.compiler.lexer

import java.io.File
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class TokenListBuilder {

    private val backing = mutableListOf<TokenType>()

    fun build() = backing.toList()

    private fun baseToken(name: String, regex: Regex, constructor: TokenType.(Int, String, File) -> Token?): TokenType {
        val result = object : TokenType {

            override val regex: Regex = regex

            override val name: String = name

            override fun new(index: Int, content: String, file: File): Token? = constructor(index, content, file)
        }
        backing.add(result)
        return result
    }

    fun token(name: String, regex: Regex): TokenType = baseToken(name, regex) { index, content, file ->
        val type = this
        object : Token {

            override val type: TokenType = type

            override val index: Int = index

            override val content: String = content

            override val file: File = file

        }
    }

    fun ignored(name: String, regex: Regex): TokenType = baseToken(name, regex) { _, _, _ -> null }

    fun token(regex: Regex): TokenTypeDelegate = TokenTypeDelegate(regex, ::token)

    fun ignored(regex: Regex): TokenTypeDelegate = TokenTypeDelegate(regex, ::ignored)

}

fun tokens(block: TokenListBuilder.() -> Unit): List<TokenType> =
    TokenListBuilder().apply(block).build()

private val tokenListBuilder = TokenListBuilder()

val tokens: List<TokenType> get() = tokenListBuilder.build()

fun token(name: String, regex: Regex): TokenType = tokenListBuilder.token(name, regex)

fun ignored(name: String, regex: Regex): TokenType = tokenListBuilder.ignored(name, regex)

fun token(regex: Regex): TokenTypeDelegate = TokenTypeDelegate(regex, ::token)

fun ignored(regex: Regex): TokenTypeDelegate = TokenTypeDelegate(regex, ::ignored)

class TokenTypeDelegate(private val regex: Regex, val createToken: (name: String, regex: Regex) -> TokenType) :
    ReadOnlyProperty<Any?, TokenType> {

    private var _values: MutableMap<String, TokenType> = mutableMapOf()

    override fun getValue(thisRef: Any?, property: KProperty<*>): TokenType =
        _values.computeIfAbsent(property.name) { createToken(it, regex) }

}
