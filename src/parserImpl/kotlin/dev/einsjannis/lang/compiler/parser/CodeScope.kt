package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.compiler.parser.*
import dev.einsjannis.lang.compiler.Token
import dev.einsjannis.tupleOf

class CodeScope(val list: List<Code>) : ScopeNode, List<Node?> by list {

    override var parent: Node? = null

    companion object : Pattern<CodeScope> by scopePattern(
        elementPattern = { Code },
        separatorPattern = Token.Symbol.SemiColon.pattern,
        limiterPatterns = tupleOf(
            Token.Symbol.Brackets.ParenthesesL.pattern,
            Token.Symbol.Brackets.ParenthesesR.pattern
        ),
        constructor = ::CodeScope,
        requireTrailing = true
    )

}
