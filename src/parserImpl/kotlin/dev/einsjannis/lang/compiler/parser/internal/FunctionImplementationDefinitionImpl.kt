package dev.einsjannis.lang.compiler.parser.internal

import dev.einsjannis.compiler.lexer.Token
import dev.einsjannis.lang.compiler.ir.*

class FunctionImplementationDefinitionImpl(
    identifierToken: Token,
    override val arguments: ArgumentDefinitionScope,
    override val returnType: ReturnType,
    override val code: CodeScope
) : FunctionImplementationDefinition {

    override val name: String = identifierValueOf(identifierToken)

}
