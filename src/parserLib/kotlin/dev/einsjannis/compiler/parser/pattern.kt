package dev.einsjannis.compiler.parser

import dev.einsjannis.AdvancedIterator
import dev.einsjannis.Tuple2
import dev.einsjannis.castTo
import dev.einsjannis.compiler.lexer.Token
import dev.einsjannis.compiler.lexer.TokenType
import dev.einsjannis.tupleOf

interface Pattern<T : Node?> {

    fun match(tokens: AdvancedIterator<Token>): Match<T>

}

fun <T : Node?> AdvancedIterator<Token>.match(pattern: Pattern<T>) = pattern.match(this)

val TokenType.pattern: Pattern<TokenNode> get() {
    val tokenType = this
    return object : Pattern<TokenNode> {

        override fun match(tokens: AdvancedIterator<Token>): Match<TokenNode> {
            if (tokens.hasNext()) return NoMatch(NoMatch.Cause.NoTokensLeft(tokenType))
            val match = tokens.next()
            if (match.type != tokenType) {
                tokens.previous()
                return NoMatch(NoMatch.Cause.TokenMissMatch(tokenType))
            }
            return ValidMatch(TokenNode(match))
        }

    }
}

fun <T : Node> optional(pattern: Pattern<T>) = object : Pattern<T?> {

    override fun match(tokens: AdvancedIterator<Token>): Match<T?> {
        val match = tokens.match(pattern)
        if (match is NoMatch) {
            if(match.causedBy<NoMatch.Cause.NoTokensLeft>())
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            return ValidMatch(null)
        }
        return ValidMatch(match.node)
    }

}

fun <T : Node> superPattern(patterns: List<Pattern<T>>) = object : Pattern<T> {

    override fun match(tokens: AdvancedIterator<Token>): Match<T> {
        val failed = mutableListOf<Tuple2<Any, Pattern<T>, NoMatch.Cause>>()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                failed.add(tupleOf(pattern, match.cause))
                continue
            }
            return match
        }
        return NoMatch(NoMatch.Cause.PatternMissMatches(failed.map { it.castTo(NoMatch.Cause::PatternMissMatch) }))
    }

}

fun <E : Node, S : Node, LS : Node, LE : Node> scopePattern(elementPattern: Pattern<E>, separatorPattern: Pattern<S>, limiterPatterns: Tuple2<Pattern<*>, Pattern<LS>, Pattern<LE>>, requireTrailing: Boolean = false) = object : Pattern<ScopeNode> {

    override fun match(tokens: AdvancedIterator<Token>): Match<ScopeNode> {
        val (startPattern, endPattern) = limiterPatterns
        tokens.pushContext()
        val startMatch = tokens.match(startPattern)
        if (startMatch is NoMatch)
            return NoMatch(NoMatch.Cause.PatternMissMatch(startPattern, startMatch.cause))
        val list = buildList <Node> {
            while (true) {
                val elementMatch = tokens.match(elementPattern)
                if (elementMatch is NoMatch) {
                    tokens.popContext()
                    return NoMatch(NoMatch.Cause.PatternMissMatch(elementPattern, elementMatch.cause))
                }
                val separatorMatch = tokens.match(separatorPattern)
                if (separatorMatch is NoMatch) {
                    if (!requireTrailing) {
                        val endMatch = tokens.match(endPattern)
                        if (endMatch is ValidMatch) {
                            add(elementMatch.node)
                            break
                        }
                    }
                    tokens.popContext()
                    return NoMatch(NoMatch.Cause.PatternMissMatch(separatorPattern, separatorMatch.cause))
                }
                add(elementMatch.node)
                val endMatch = tokens.match(endPattern)
                if (endMatch !is NoMatch) break
                if (endMatch.causedBy<NoMatch.Cause.NoTokensLeft>()) return NoMatch(NoMatch.Cause.PatternMissMatch(endPattern, endMatch.cause))
            }
        }
        return ValidMatch(object : ScopeNode, List<Node?> by list {
            override var parent: Node? = null
        })
    }

}
