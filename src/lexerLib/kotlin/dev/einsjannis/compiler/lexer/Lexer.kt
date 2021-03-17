package dev.einsjannis.compiler.lexer

import java.io.File
import kotlin.contracts.contract

class Lexer(private val tokens: List<TokenType>) {

    fun lex(file: File) = buildList {
        var index = 0
        val content = file.readText()
        while (index < content.length) {
            index = nextToken(index, content, file)
        }
    }

    private fun MutableList<Token>.nextToken(index: Int, content: String, file: File): Int {
        for (token in tokens) {
            val match = token.regex.find(content, index)
            if (!isMatchValid(match, atIndex = index)) continue else {
                token.new(index, match.value, file)?.also { add(it) }
                return match.range.last + 1
            }
        }
        throw UnknownTokenException(index, content, file)
    }

    private fun isMatchValid(match: MatchResult?, atIndex: Int): Boolean {
        contract { returns(true) implies(match != null) }
        return match != null && match.range.first == atIndex
    }

}
