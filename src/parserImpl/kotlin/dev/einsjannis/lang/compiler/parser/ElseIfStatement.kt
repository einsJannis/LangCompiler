package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.Tuple3
import dev.einsjannis.compiler.parser.*
import dev.einsjannis.lang.compiler.Token
import dev.einsjannis.tupleOf

data class ElseIfStatement(
    val condition: Expression,
    val code: CodeScope,
    val elseStatement: BaseElseStatement?
) : BaseElseStatement, Tuple3<Node?, Expression, CodeScope, BaseElseStatement?> {

    override var parent: Node? = null

    companion object : Pattern<ElseIfStatement> by sequence7(
        tupleOf(
            Token.Keyword.Else.pattern,
            Token.Keyword.If.pattern,
            Token.Symbol.Brackets.BracesL.pattern,
            Expression,
            Token.Symbol.Brackets.BracesR.pattern,
            CodeScope,
            optional(BaseElseStatement)
        ),
        { (_, _, _, expression, _, codeScope, elseStatement) -> ElseIfStatement(expression, codeScope, elseStatement) }
    )

}
