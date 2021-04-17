package dev.einsjannis.lang.compiler.codegeneration

import dev.einsjannis.lang.compiler.ir.*
import dev.einsjannis.lang.compiler.ir.builtin.Types

fun generateLLVMCode(definitions: DefinitionScope): String =
    StringBuilder().apply { appendDefinitions(definitions) }.toString()

private fun StringBuilder.appendDefinitions(definitions: DefinitionScope) =
    definitions.children.forEach { appendDefinition(it) }

private fun StringBuilder.appendDefinition(definition: Definition) = when (definition) {
    is StructDefinition -> appendStructDefinition(definition)
    is VariableDefinition -> appendVariableDefinition(definition)
    is FunctionDefinition -> appendFunctionDefinition(definition)
    else -> throw Exception("hOw?¿?")
}

private fun StringBuilder.appendStructDefinition(definition: StructDefinition) {
    appendVariableName(definition.name)
    append(" = type { ")
    appendStructVariableDefinitions(definition.variableDefinitions)
    append(" }")
}

private fun StringBuilder.appendStructVariableDefinitions(definitions: StructVariableDefinitionScope) =
    definitions.children.forEach { appendStructVariableDefinition(it) }

private fun StringBuilder.appendStructVariableDefinition(definition: VariableDefinition) {
    appendReturnType(definition.returnType)
}

fun StringBuilder.appendReturnType(returnType: ReturnType) {
    appendType(returnType.typeDefinition)
    if (returnType.isPointer) append("*")
}

private fun StringBuilder.appendType(typeDefinition: TypeDefinition) {
    when (typeDefinition) {
        is Types.Byte -> append("i8")
        is Types.Integer -> append("i32")
        is Types.Long -> append("i64")
        else -> appendVariableName(typeDefinition.name)
    }
}

private fun StringBuilder.appendVariableDefinition(definition: VariableDefinition) {
    appendVariableName(definition.name)
    TODO()
}

private fun StringBuilder.appendFunctionDefinition(definition: FunctionDefinition) {
    append("define ")
    appendReturnType(definition.returnType)
    append(" ")
    appendFunctionName(definition.name)
    appendFunctionArguments(definition.arguments)
    TODO()
}

private fun StringBuilder.appendFunctionArguments(arguments: ArgumentDefinitionScope) =
    arguments.children.forEachIndexed { i, it -> appendFunctionArgument(it, i < arguments.children.lastIndex) }

fun StringBuilder.appendFunctionArgument(argument: ArgumentDefinition, isLast: Boolean) {
    appendReturnType(argument.returnType)
    if (!isLast) append(", ")
}

private fun StringBuilder.appendVariableName(name: String) {
    append("%")
    append(name)
}

private fun StringBuilder.appendFunctionName(name: String) {
    append("@")
    append(name)
}
