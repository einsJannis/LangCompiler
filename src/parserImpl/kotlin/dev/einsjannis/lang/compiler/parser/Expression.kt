package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.compiler.parser.Pattern
import dev.einsjannis.compiler.parser.superPattern

interface Expression : Code {

    companion object : Pattern<Expression> by superPattern(listOf(Primitive, FunctionCall, Identifier, IfStatement))

}
