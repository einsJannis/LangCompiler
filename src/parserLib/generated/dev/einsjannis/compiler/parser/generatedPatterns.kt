@file:Suppress("UNUSED")
package dev.einsjannis.compiler.parser

import dev.einsjannis.*
import dev.einsjannis.plus
import dev.einsjannis.compiler.lexer.*

fun <N : Any> sequence0(patterns: Tuple0<Pattern<*>>, constructor: (Tuple0<Any?>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Any?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.removeContext()
        return ValidMatch(constructor(tuple as Tuple0<Any?>))
    }

}

fun <N : Any, T0> sequence1(patterns: Tuple1<Pattern<*>, Pattern<T0>>, constructor: (Tuple1<Any?, T0>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Any?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.removeContext()
        return ValidMatch(constructor(tuple as Tuple1<Any?, T0>))
    }

}

fun <N : Any, T0, T1> sequence2(patterns: Tuple2<Pattern<*>, Pattern<T0>, Pattern<T1>>, constructor: (Tuple2<Any?, T0, T1>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Any?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.removeContext()
        return ValidMatch(constructor(tuple as Tuple2<Any?, T0, T1>))
    }

}

fun <N : Any, T0, T1, T2> sequence3(patterns: Tuple3<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>>, constructor: (Tuple3<Any?, T0, T1, T2>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Any?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.removeContext()
        return ValidMatch(constructor(tuple as Tuple3<Any?, T0, T1, T2>))
    }

}

fun <N : Any, T0, T1, T2, T3> sequence4(patterns: Tuple4<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>>, constructor: (Tuple4<Any?, T0, T1, T2, T3>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Any?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.removeContext()
        return ValidMatch(constructor(tuple as Tuple4<Any?, T0, T1, T2, T3>))
    }

}

fun <N : Any, T0, T1, T2, T3, T4> sequence5(patterns: Tuple5<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>>, constructor: (Tuple5<Any?, T0, T1, T2, T3, T4>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Any?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.removeContext()
        return ValidMatch(constructor(tuple as Tuple5<Any?, T0, T1, T2, T3, T4>))
    }

}

fun <N : Any, T0, T1, T2, T3, T4, T5> sequence6(patterns: Tuple6<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>>, constructor: (Tuple6<Any?, T0, T1, T2, T3, T4, T5>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Any?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.removeContext()
        return ValidMatch(constructor(tuple as Tuple6<Any?, T0, T1, T2, T3, T4, T5>))
    }

}

fun <N : Any, T0, T1, T2, T3, T4, T5, T6> sequence7(patterns: Tuple7<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>, Pattern<T6>>, constructor: (Tuple7<Any?, T0, T1, T2, T3, T4, T5, T6>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Any?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.removeContext()
        return ValidMatch(constructor(tuple as Tuple7<Any?, T0, T1, T2, T3, T4, T5, T6>))
    }

}

fun <N : Any, T0, T1, T2, T3, T4, T5, T6, T7> sequence8(patterns: Tuple8<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>, Pattern<T6>, Pattern<T7>>, constructor: (Tuple8<Any?, T0, T1, T2, T3, T4, T5, T6, T7>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Any?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.removeContext()
        return ValidMatch(constructor(tuple as Tuple8<Any?, T0, T1, T2, T3, T4, T5, T6, T7>))
    }

}

fun <N : Any, T0, T1, T2, T3, T4, T5, T6, T7, T8> sequence9(patterns: Tuple9<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>, Pattern<T6>, Pattern<T7>, Pattern<T8>>, constructor: (Tuple9<Any?, T0, T1, T2, T3, T4, T5, T6, T7, T8>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Any?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.removeContext()
        return ValidMatch(constructor(tuple as Tuple9<Any?, T0, T1, T2, T3, T4, T5, T6, T7, T8>))
    }

}

fun <N : Any, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9> sequence10(patterns: Tuple10<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>, Pattern<T6>, Pattern<T7>, Pattern<T8>, Pattern<T9>>, constructor: (Tuple10<Any?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Any?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.removeContext()
        return ValidMatch(constructor(tuple as Tuple10<Any?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9>))
    }

}

fun <N : Any, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> sequence11(patterns: Tuple11<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>, Pattern<T6>, Pattern<T7>, Pattern<T8>, Pattern<T9>, Pattern<T10>>, constructor: (Tuple11<Any?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Any?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.removeContext()
        return ValidMatch(constructor(tuple as Tuple11<Any?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>))
    }

}

fun <N : Any, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> sequence12(patterns: Tuple12<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>, Pattern<T6>, Pattern<T7>, Pattern<T8>, Pattern<T9>, Pattern<T10>, Pattern<T11>>, constructor: (Tuple12<Any?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Any?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.removeContext()
        return ValidMatch(constructor(tuple as Tuple12<Any?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>))
    }

}

fun <N : Any, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> sequence13(patterns: Tuple13<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>, Pattern<T6>, Pattern<T7>, Pattern<T8>, Pattern<T9>, Pattern<T10>, Pattern<T11>, Pattern<T12>>, constructor: (Tuple13<Any?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Any?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.removeContext()
        return ValidMatch(constructor(tuple as Tuple13<Any?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>))
    }

}

fun <N : Any, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> sequence14(patterns: Tuple14<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>, Pattern<T6>, Pattern<T7>, Pattern<T8>, Pattern<T9>, Pattern<T10>, Pattern<T11>, Pattern<T12>, Pattern<T13>>, constructor: (Tuple14<Any?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Any?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.removeContext()
        return ValidMatch(constructor(tuple as Tuple14<Any?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>))
    }

}

fun <N : Any, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> sequence15(patterns: Tuple15<Pattern<*>, Pattern<T0>, Pattern<T1>, Pattern<T2>, Pattern<T3>, Pattern<T4>, Pattern<T5>, Pattern<T6>, Pattern<T7>, Pattern<T8>, Pattern<T9>, Pattern<T10>, Pattern<T11>, Pattern<T12>, Pattern<T13>, Pattern<T14>>, constructor: (Tuple15<Any?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>) -> N) = object : Pattern<N> {

    @Suppress("UNCHECKED_CAST")
    override fun match(tokens: AdvancedIterator<Token>): Match<N> {
        tokens.pushContext()
        var tuple: Tuple<Any?> = tupleOf()
        for (pattern in patterns) {
            val match = tokens.match(pattern)
            if (match is NoMatch) {
                tokens.popContext()
                return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
            }
            tuple = tuple.plus(match.node)
        }
        tokens.removeContext()
        return ValidMatch(constructor(tuple as Tuple15<Any?, T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>))
    }

}
