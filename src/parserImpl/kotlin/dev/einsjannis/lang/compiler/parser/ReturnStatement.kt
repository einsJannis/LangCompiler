package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.Tuple1
import dev.einsjannis.compiler.parser.Node
import dev.einsjannis.compiler.parser.Pattern
import dev.einsjannis.compiler.parser.pattern
import dev.einsjannis.compiler.parser.sequence2
import dev.einsjannis.lang.compiler.Token
import dev.einsjannis.tupleOf

data class ReturnStatement(val expression: Expression) : Code, Tuple1<Node?, Expression> {

    override var parent: Node? = null

    companion object : Pattern<ReturnStatement> by sequence2(
        tupleOf(Token.Keyword.Return.pattern, Expression),
        { (_, expression) -> ReturnStatement(expression) }
    )

}
