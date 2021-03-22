package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.Tuple2
import dev.einsjannis.compiler.parser.Node
import dev.einsjannis.compiler.parser.Pattern
import dev.einsjannis.compiler.parser.pattern
import dev.einsjannis.compiler.parser.sequence3
import dev.einsjannis.lang.compiler.Token
import dev.einsjannis.tupleOf

data class Assignment(
    val identifier: Identifier,
    val expression: Expression
) : Code, Tuple2<Node?, Identifier, Expression> {

    override var parent: Node? = null

    companion object : Pattern<Assignment> by sequence3(
        tupleOf(Identifier, Token.Symbol.Assign.pattern, Expression),
        { (identifier, _, expression) -> Assignment(identifier, expression) }
    )

}
