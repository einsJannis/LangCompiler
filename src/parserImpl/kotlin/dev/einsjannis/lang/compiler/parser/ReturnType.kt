package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.Tuple1
import dev.einsjannis.compiler.parser.Node
import dev.einsjannis.compiler.parser.Pattern
import dev.einsjannis.compiler.parser.pattern
import dev.einsjannis.compiler.parser.sequence2
import dev.einsjannis.lang.compiler.Token
import dev.einsjannis.tupleOf

data class ReturnType(val identifier: Identifier): Node, Tuple1<Node?, Identifier> {

    override var parent: Node? = null

    companion object : Pattern<ReturnType> by sequence2(
        tupleOf(Token.Symbol.ReturnType.pattern, Identifier),
        { (_, identifier) -> ReturnType(identifier) }
    )

}
