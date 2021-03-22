package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.compiler.parser.*
import dev.einsjannis.lang.compiler.Token
import dev.einsjannis.tupleOf

data class Number(override val token: TokenNode) : Primitive {

    override var parent: Node? = null

    companion object : Pattern<Number> by sequence1(
        tupleOf(Token.Primitive.Number.pattern),
        { (primitive) -> Number(primitive) }
    )

}
