package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.compiler.parser.Node
import dev.einsjannis.compiler.parser.Pattern
import dev.einsjannis.compiler.parser.superPattern

interface Code : Node {

    companion object : Pattern<Code> by superPattern(listOf(Expression, Assignment, VariableDefinition, ReturnStatement))

}
