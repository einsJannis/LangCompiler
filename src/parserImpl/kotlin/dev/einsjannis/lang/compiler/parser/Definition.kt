package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.Tuple2
import dev.einsjannis.compiler.parser.Node
import dev.einsjannis.compiler.parser.Pattern
import dev.einsjannis.compiler.parser.superPattern

interface Definition : Node, Tuple2<Node?, Identifier, ReturnType> {

    val identifier: Identifier

    val returnType: ReturnType

    companion object : Pattern<Definition> by superPattern(listOf(VariableDefinition, FunctionDefinition))

}
