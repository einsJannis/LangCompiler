package dev.einsjannis.compiler.parser

import dev.einsjannis.Tuple0
import dev.einsjannis.compiler.lexer.Token

data class TokenNode(val token: Token) : Node, Tuple0<Node?> {

    override var parent: Node? = null

}
