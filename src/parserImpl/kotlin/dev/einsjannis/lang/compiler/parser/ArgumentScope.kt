package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.compiler.parser.*
import dev.einsjannis.lang.compiler.Token
import dev.einsjannis.tupleOf

class ArgumentScope(val list: List<Expression>) : ScopeNode, List<Node?> by list {

    override var parent: Node? = null

    companion object : Pattern<ArgumentScope> by scopePattern(
        elementPattern = { Expression },
        separatorPattern = Token.Symbol.Colon.pattern,
        limiterPatterns = tupleOf(Token.Symbol.Brackets.BracesL.pattern, Token.Symbol.Brackets.BracesR.pattern),
        constructor = ::ArgumentScope
    )

}
