package dev.einsjannis.compiler.parser

import dev.einsjannis.AdvancedIterator
import dev.einsjannis.compiler.lexer.Token

class FileParser<T : Node?>(private val rootPattern: Pattern<T>) {

    fun parse(tokens: List<Token>): T {
        val tokenIterator = AdvancedIterator(tokens)
        val match = rootPattern.match(tokenIterator)
        if (match is NoMatch) {
            match.cause.printTree()
            throw TODO()
        }
        return match.node
    }

}
