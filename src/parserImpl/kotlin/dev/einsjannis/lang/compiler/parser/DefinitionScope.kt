package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.AdvancedIterator
import dev.einsjannis.compiler.parser.*
import dev.einsjannis.lang.compiler.Token

class DefinitionScope(val list: List<Definition>) : ScopeNode, List<Node?> by list {

    override var parent: Node? = null

    companion object : Pattern<DefinitionScope> {

        override fun match(tokens: AdvancedIterator<dev.einsjannis.compiler.lexer.Token>): Match<DefinitionScope> {
            tokens.pushContext()
            val list = buildList {
                while (true) {
                    val elementMatch = tokens.match(Definition)
                    if (elementMatch is NoMatch) {
                        tokens.popContext()
                        return NoMatch(NoMatch.Cause.PatternMissMatch(Definition, elementMatch.cause))
                    }
                    val separatorMatch = tokens.match(Token.Symbol.SemiColon.pattern)
                    if (separatorMatch is NoMatch) {
                        tokens.popContext()
                        return NoMatch(
                            NoMatch.Cause.PatternMissMatch(
                                Token.Symbol.SemiColon.pattern,
                                separatorMatch.cause
                            )
                        )
                    }
                    add(elementMatch.node)
                    if (!tokens.hasNext()) break
                }
            }
            tokens.clearContext()
            return ValidMatch(DefinitionScope(list))
        }

    }

}
