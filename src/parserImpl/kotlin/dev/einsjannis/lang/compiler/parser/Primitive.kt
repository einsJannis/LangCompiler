package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.Tuple1
import dev.einsjannis.compiler.parser.Node
import dev.einsjannis.compiler.parser.Pattern
import dev.einsjannis.compiler.parser.TokenNode
import dev.einsjannis.compiler.parser.superPattern

interface Primitive : Expression, Tuple1<Node?, TokenNode> {

    val token: TokenNode

    companion object : Pattern<Primitive> by superPattern(listOf(Number, String, Char, Boolean))

}
