package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.AdvancedIterator
import dev.einsjannis.compiler.lexer.Token
import dev.einsjannis.compiler.parser.Match
import dev.einsjannis.compiler.parser.Node
import dev.einsjannis.compiler.parser.Pattern
import dev.einsjannis.compiler.parser.superPattern

interface BaseElseStatement : Node {

    companion object : Pattern<BaseElseStatement> {

        private val backing: Pattern<BaseElseStatement> by lazy { superPattern(listOf(ElseStatement, ElseIfStatement)) }

        override fun match(tokens: AdvancedIterator<Token>): Match<BaseElseStatement> = backing.match(tokens)

    }

}
