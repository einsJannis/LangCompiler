package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.AdvancedIterator
import dev.einsjannis.compiler.lexer.Token
import dev.einsjannis.compiler.parser.*

interface BaseElseStatement : Node {

    companion object : Pattern<BaseElseStatement> {

        val backing: Pattern<BaseElseStatement> by lazy { superPattern(listOf(ElseStatement, ElseIfStatement)) }

        override fun match(tokens: AdvancedIterator<Token>): Match<BaseElseStatement> = backing.match(tokens)

    }

}
