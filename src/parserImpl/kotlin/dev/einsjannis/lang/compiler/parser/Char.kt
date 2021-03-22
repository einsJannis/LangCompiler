package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.castTo
import dev.einsjannis.compiler.parser.*
import dev.einsjannis.lang.compiler.Token
import dev.einsjannis.tupleOf

data class Char(override val token: TokenNode) : Primitive {

    override var parent: Node? = null

    companion object : Pattern<Char> by sequence1(
        tupleOf(Token.Primitive.Char.pattern),
        { it.castTo(::Char) }
    )

}
