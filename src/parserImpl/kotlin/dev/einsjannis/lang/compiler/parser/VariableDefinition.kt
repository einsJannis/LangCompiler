package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.Tuple3
import dev.einsjannis.tupleOf
import dev.einsjannis.castTo
import dev.einsjannis.compiler.parser.*
import dev.einsjannis.lang.compiler.Token

data class VariableDefinition(
    override val identifier: Identifier,
    override val returnType: ReturnType,
    val expression: Expression
) : Definition, Code, Tuple3<Node?, Identifier, ReturnType, Expression> {

    override var parent: Node? = null

    companion object : Pattern<VariableDefinition> by sequence5(
        tupleOf(Token.Keyword.Variable.pattern, Identifier, ReturnType, Token.Symbol.Assign.pattern, Expression),
        { (_, identifier, returnType, _, expression) -> VariableDefinition(identifier, returnType, expression) }
    )

}
