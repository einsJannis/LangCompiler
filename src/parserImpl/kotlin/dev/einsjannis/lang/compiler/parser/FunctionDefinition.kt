package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.Tuple4
import dev.einsjannis.compiler.parser.Node
import dev.einsjannis.compiler.parser.Pattern
import dev.einsjannis.compiler.parser.pattern
import dev.einsjannis.compiler.parser.sequence5
import dev.einsjannis.lang.compiler.Token
import dev.einsjannis.tupleOf

data class FunctionDefinition(
    override val identifier: Identifier,
    override val returnType: ReturnType,
    val arguments: ArgumentDefinitionScope,
    val code: CodeScope
) : Definition, Tuple4<Node?, Identifier, ReturnType, ArgumentDefinitionScope, CodeScope> {

    override var parent: Node? = null

    companion object : Pattern<FunctionDefinition> by sequence5(
        tupleOf(Token.Keyword.Function.pattern, Identifier, ArgumentDefinitionScope, ReturnType, CodeScope),
        { (_, identifier, arguments, returnType, code) -> FunctionDefinition(identifier, returnType, arguments, code) }
    )

}
