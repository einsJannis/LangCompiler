package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.Tuple2
import dev.einsjannis.castTo
import dev.einsjannis.compiler.parser.Node
import dev.einsjannis.compiler.parser.Pattern
import dev.einsjannis.compiler.parser.sequence2
import dev.einsjannis.tupleOf

data class ArgumentDefinition(
    override val identifier: Identifier,
    override val returnType: ReturnType
) : Definition, Tuple2<Node?, Identifier, ReturnType> {

    override var parent: Node? = null

    companion object : Pattern<ArgumentDefinition> by sequence2(
        tupleOf(Identifier, ReturnType),
        { it.castTo(::ArgumentDefinition) }
    )

}
