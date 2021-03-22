package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.compiler.parser.*
import dev.einsjannis.lang.compiler.Token
import dev.einsjannis.tupleOf

data class String(override val token: TokenNode) : Primitive {

    override var parent: Node? = null

    companion object : Pattern<String> by sequence1(
        tupleOf(Token.Primitive.String.pattern),
        { (token) -> String(token) }
    )

}
