package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.Tuple1
import dev.einsjannis.compiler.parser.Node
import dev.einsjannis.compiler.parser.Pattern
import dev.einsjannis.compiler.parser.pattern
import dev.einsjannis.compiler.parser.sequence2
import dev.einsjannis.lang.compiler.Token
import dev.einsjannis.tupleOf

data class ElseStatement(val code: CodeScope) : BaseElseStatement, Tuple1<Node?, CodeScope> {

    override var parent: Node? = null

    companion object : Pattern<ElseStatement> by sequence2(
        tupleOf(Token.Keyword.Else.pattern, CodeScope),
        { (_, code) -> ElseStatement(code) },
    )

}
