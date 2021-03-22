@file:Suppress("UNUSED")
package dev.einsjannis.compiler.parser

import dev.einsjannis.*
import dev.einsjannis.plus
import dev.einsjannis.compiler.lexer.*

fun <N : Node> sequence0(patterns: Tuple0<Pattern<*>>, constructor: (Tuple0<Node?>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Node?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.clearContext()
        return ValidMatch(constructor(tuple as Tuple0<Node?>))
    }

}

fun <N : Node, T0 : Node?> sequence1(patterns: Tuple1<Pattern<*>, Pattern<T0>>, constructor: (Tuple1<Node?, T0>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Node?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.clearContext()
        return ValidMatch(constructor(tuple as Tuple1<Node?, T0>))
    }

}

fun <N : Node, T0 : Node?, T1 : Node?> sequence2(patterns: Tuple2<Pattern<*>, Pattern<T0>, Pattern<T1>>, constructor: (Tuple2<Node?, T0, T1>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Node?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.clearContext()
        return ValidMatch(constructor(tuple as Tuple2<Node?, T0, T1>))
    }

}

fun <N : Node, T0 : Node?, T1 : Node?, T2 : Node?> sequence3(patterns: Tuple3<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>>, constructor: (Tuple3<Node?, T0, T1, T2>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Node?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.clearContext()
        return ValidMatch(constructor(tuple as Tuple3<Node?, T0, T1, T2>))
    }

}

fun <N : Node, T0 : Node?, T1 : Node?, T2 : Node?, T3 : Node?> sequence4(patterns: Tuple4<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>>, constructor: (Tuple4<Node?, T0, T1, T2, T3>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Node?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.clearContext()
        return ValidMatch(constructor(tuple as Tuple4<Node?, T0, T1, T2, T3>))
    }

}

fun <N : Node, T0 : Node?, T1 : Node?, T2 : Node?, T3 : Node?, T4 : Node?> sequence5(patterns: Tuple5<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>>, constructor: (Tuple5<Node?, T0, T1, T2, T3, T4>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Node?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.clearContext()
        return ValidMatch(constructor(tuple as Tuple5<Node?, T0, T1, T2, T3, T4>))
    }

}

fun <N : Node, T0 : Node?, T1 : Node?, T2 : Node?, T3 : Node?, T4 : Node?, T5 : Node?> sequence6(patterns: Tuple6<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>>, constructor: (Tuple6<Node?, T0, T1, T2, T3, T4, T5>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Node?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.clearContext()
        return ValidMatch(constructor(tuple as Tuple6<Node?, T0, T1, T2, T3, T4, T5>))
    }

}

fun <N : Node, T0 : Node?, T1 : Node?, T2 : Node?, T3 : Node?, T4 : Node?, T5 : Node?, T6 : Node?> sequence7(patterns: Tuple7<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>, Pattern<T6>>, constructor: (Tuple7<Node?, T0, T1, T2, T3, T4, T5, T6>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Node?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.clearContext()
        return ValidMatch(constructor(tuple as Tuple7<Node?, T0, T1, T2, T3, T4, T5, T6>))
    }

}

fun <N : Node, T0 : Node?, T1 : Node?, T2 : Node?, T3 : Node?, T4 : Node?, T5 : Node?, T6 : Node?, T7 : Node?> sequence8(patterns: Tuple8<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>, Pattern<T6>, Pattern<T7>>, constructor: (Tuple8<Node?, T0, T1, T2, T3, T4, T5, T6, T7>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Node?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.clearContext()
        return ValidMatch(constructor(tuple as Tuple8<Node?, T0, T1, T2, T3, T4, T5, T6, T7>))
    }

}

fun <N : Node, T0 : Node?, T1 : Node?, T2 : Node?, T3 : Node?, T4 : Node?, T5 : Node?, T6 : Node?, T7 : Node?, T8 : Node?> sequence9(patterns: Tuple9<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>, Pattern<T6>, Pattern<T7>, Pattern<T8>>, constructor: (Tuple9<Node?, T0, T1, T2, T3, T4, T5, T6, T7, T8>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Node?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.clearContext()
        return ValidMatch(constructor(tuple as Tuple9<Node?, T0, T1, T2, T3, T4, T5, T6, T7, T8>))
    }

}

fun <N : Node, T0 : Node?, T1 : Node?, T2 : Node?, T3 : Node?, T4 : Node?, T5 : Node?, T6 : Node?, T7 : Node?, T8 : Node?, T9 : Node?> sequence10(patterns: Tuple10<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>, Pattern<T6>, Pattern<T7>, Pattern<T8>, Pattern<T9>>, constructor: (Tuple10<Node?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Node?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.clearContext()
        return ValidMatch(constructor(tuple as Tuple10<Node?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9>))
    }

}

fun <N : Node, T0 : Node?, T1 : Node?, T2 : Node?, T3 : Node?, T4 : Node?, T5 : Node?, T6 : Node?, T7 : Node?, T8 : Node?, T9 : Node?, T10 : Node?> sequence11(patterns: Tuple11<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>, Pattern<T6>, Pattern<T7>, Pattern<T8>, Pattern<T9>, Pattern<T10>>, constructor: (Tuple11<Node?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Node?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.clearContext()
        return ValidMatch(constructor(tuple as Tuple11<Node?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>))
    }

}

fun <N : Node, T0 : Node?, T1 : Node?, T2 : Node?, T3 : Node?, T4 : Node?, T5 : Node?, T6 : Node?, T7 : Node?, T8 : Node?, T9 : Node?, T10 : Node?, T11 : Node?> sequence12(patterns: Tuple12<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>, Pattern<T6>, Pattern<T7>, Pattern<T8>, Pattern<T9>, Pattern<T10>, Pattern<T11>>, constructor: (Tuple12<Node?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Node?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.clearContext()
        return ValidMatch(constructor(tuple as Tuple12<Node?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>))
    }

}

fun <N : Node, T0 : Node?, T1 : Node?, T2 : Node?, T3 : Node?, T4 : Node?, T5 : Node?, T6 : Node?, T7 : Node?, T8 : Node?, T9 : Node?, T10 : Node?, T11 : Node?, T12 : Node?> sequence13(patterns: Tuple13<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>, Pattern<T6>, Pattern<T7>, Pattern<T8>, Pattern<T9>, Pattern<T10>, Pattern<T11>, Pattern<T12>>, constructor: (Tuple13<Node?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Node?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.clearContext()
        return ValidMatch(constructor(tuple as Tuple13<Node?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>))
    }

}

fun <N : Node, T0 : Node?, T1 : Node?, T2 : Node?, T3 : Node?, T4 : Node?, T5 : Node?, T6 : Node?, T7 : Node?, T8 : Node?, T9 : Node?, T10 : Node?, T11 : Node?, T12 : Node?, T13 : Node?> sequence14(patterns: Tuple14<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>, Pattern<T6>, Pattern<T7>, Pattern<T8>, Pattern<T9>, Pattern<T10>, Pattern<T11>, Pattern<T12>, Pattern<T13>>, constructor: (Tuple14<Node?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Node?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.clearContext()
        return ValidMatch(constructor(tuple as Tuple14<Node?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>))
    }

}

fun <N : Node, T0 : Node?, T1 : Node?, T2 : Node?, T3 : Node?, T4 : Node?, T5 : Node?, T6 : Node?, T7 : Node?, T8 : Node?, T9 : Node?, T10 : Node?, T11 : Node?, T12 : Node?, T13 : Node?, T14 : Node?> sequence15(patterns: Tuple15<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>, Pattern<T6>, Pattern<T7>, Pattern<T8>, Pattern<T9>, Pattern<T10>, Pattern<T11>, Pattern<T12>, Pattern<T13>, Pattern<T14>>, constructor: (Tuple15<Node?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Node?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.clearContext()
        return ValidMatch(constructor(tuple as Tuple15<Node?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>))
    }

}
