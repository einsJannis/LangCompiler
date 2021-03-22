package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.compiler.parser.*
import dev.einsjannis.lang.compiler.Token
import dev.einsjannis.tupleOf

data class Boolean(override val token: TokenNode) : Primitive {

    override var parent: Node? = null

    companion object : Pattern<Boolean> by sequence1(
        tupleOf(Token.Primitive.Boolean.pattern),
        { (token) -> Boolean(token) }
    )

}
