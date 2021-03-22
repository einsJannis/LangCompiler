package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.Tuple3
import dev.einsjannis.compiler.parser.*
import dev.einsjannis.lang.compiler.Token
import dev.einsjannis.tupleOf

data class IfStatement(
    val condition: Expression,
    val code: CodeScope,
    val elseStatement: BaseElseStatement?
) : Expression, Tuple3<Node?, Expression, CodeScope, BaseElseStatement?> {

    override var parent: Node? = null

    companion object : Pattern<IfStatement> by sequence6(
        tupleOf(Token.Keyword.If.pattern, Token.Symbol.Brackets.BracesL.pattern, Expression, Token.Symbol.Brackets.BracesR.pattern, CodeScope, optional(BaseElseStatement)),
        { (_, _, condition, _, code, elseStatement) -> IfStatement(condition, code, elseStatement) }
    )

}
