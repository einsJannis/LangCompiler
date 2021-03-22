package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.Tuple1
import dev.einsjannis.castTo
import dev.einsjannis.compiler.parser.*
import dev.einsjannis.lang.compiler.Token
import dev.einsjannis.tupleOf

data class Identifier(val identifierToken: TokenNode) : Expression, Tuple1<Node?, TokenNode> {

    override var parent: Node? = null

    companion object : Pattern<Identifier> by sequence1(
        tupleOf(Token.Identifier.pattern),
        { it.castTo(::Identifier) }
    )

}
