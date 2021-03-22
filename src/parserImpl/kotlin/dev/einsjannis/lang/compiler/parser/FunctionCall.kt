package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.Tuple2
import dev.einsjannis.castTo
import dev.einsjannis.compiler.parser.Node
import dev.einsjannis.compiler.parser.Pattern
import dev.einsjannis.compiler.parser.sequence2
import dev.einsjannis.tupleOf

data class FunctionCall(
    val identifier: Identifier,
    val arguments: ArgumentScope
) : Expression, Tuple2<Node?, Identifier, ArgumentScope> {

    override var parent: Node? = null

    companion object : Pattern<FunctionCall> by sequence2(
        tupleOf(Identifier, ArgumentScope),
        { it.castTo(::FunctionCall) }
    )

}
