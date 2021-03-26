package dev.einsjannis.lang.compiler.semanticAnalysis

import dev.einsjannis.lang.compiler.ir.*
import dev.einsjannis.lang.compiler.ir.builtin.*
import dev.einsjannis.lang.compiler.parser.internal.FunctionCallImpl
import dev.einsjannis.lang.compiler.parser.internal.ReturnTypeImpl
import dev.einsjannis.lang.compiler.parser.internal.VariableCallImpl

object SemanticAnalyser {

    fun analyse(tree: DefinitionScope) {
        val globalStructs: MutableList<TypeDefinition> = Types.all.toMutableList()
        val globalVariables: MutableList<VariableDefinition> = mutableListOf()
        val globalFunctions: MutableList<FunctionDefinition> = Functions.all.toMutableList()
        for (definition in tree.children) {
            when (definition) {
                is StructDefinition -> {
                    analyseStruct(definition, globalStructs, globalVariables, globalFunctions)
                    globalStructs += definition
                }
                is VariableDefinition -> {
                    analyseVariable(definition, globalStructs, globalVariables, globalFunctions)
                    globalVariables += definition
                }
                is FunctionImplementationDefinition -> {
                    analyseFunction(definition, globalStructs, globalVariables, globalFunctions)
                    globalFunctions += definition
                }
            }
        }
    }

    private fun analyseStruct(
        structDefinition: StructDefinition,
        scopeStructs: List<TypeDefinition>,
        scopeVariables: List<VariableDefinition>,
        scopeFunctions: List<FunctionDefinition>
    ) {
        val variables: MutableList<VariableDefinition> = mutableListOf()
        analyseIdentifier(structDefinition.name, scopeStructs, scopeVariables, scopeFunctions)
        structDefinition.variableDefinitions.children.forEach {
            analyseStructVariable(it, scopeStructs, variables)
            variables += it
        }
    }

    private fun analyseStructVariable(
        variable: VariableDefinition,
        globalStructs: List<TypeDefinition>,
        scopeVariables: List<VariableDefinition>
    ) {
        analyseIdentifier(variable.name, scopeVariables)
        analyseReturnType(variable.returnType, globalStructs)
    }

    private fun analyseIdentifier(identifier: String, vararg definitions: List<Definition>) {
        if (definitions.any { list -> list.any { it.name == identifier } })
            throw TODO("Name already registered")
    }

    private fun analyseVariable(variableDefinition: VariableDefinition, scopeStructs: List<TypeDefinition>, scopeVariables: List<VariableDefinition>, scopeFunctions: List<FunctionDefinition>) {
        analyseIdentifier(variableDefinition.name, scopeStructs, scopeVariables, scopeFunctions)
        analyseReturnType(variableDefinition.returnType, scopeStructs)
        variableDefinition.initialization?.let { analyseExpression(it, scopeVariables, scopeFunctions) }
    }

    private fun analyseReturnType(returnType: ReturnType, scopeStructs: List<TypeDefinition>) {
        if (returnType is ReturnTypeImpl)
        returnType.typeDefinition = scopeStructs.find { it.name == returnType.name } ?: throw TODO("UnknownRef")
    }

    private fun analyseFunction(functionDefinition: FunctionImplementationDefinition, scopeStructs: List<TypeDefinition>, scopeVariables: List<VariableDefinition>, scopeFunctions: List<FunctionDefinition>) {
        analyseIdentifier(functionDefinition.name, scopeStructs, scopeVariables, scopeFunctions)
        analyseReturnType(functionDefinition.returnType, scopeStructs)
        val mScopeVariables = scopeVariables.toMutableList()
        functionDefinition.arguments.children.forEach {
            analyseArgument(it, scopeStructs, scopeVariables)
            mScopeVariables.add(it)
        }
        functionDefinition.code.children.forEach { analyseCode(it, scopeStructs, mScopeVariables, listOf(functionDefinition).plus(scopeFunctions)) }
    }

    private fun analyseArgument(
        argumentDefinition: ArgumentDefinition,
        scopeStructs: List<TypeDefinition>,
        scopeVariables: List<VariableDefinition>
    ) {
        analyseIdentifier(argumentDefinition.name, scopeVariables)
        analyseReturnType(argumentDefinition.returnType, scopeStructs)
    }

    private fun analyseCode(code: Code, scopeStructs: List<TypeDefinition>, scopeVariables: List<VariableDefinition>, scopeFunctions: List<FunctionDefinition>) {
        val mScopeVariables: MutableList<VariableDefinition> = scopeVariables.toMutableList()
        when (code) {
            is Expression -> analyseExpression(code, mScopeVariables, scopeFunctions)
            is VariableDefinition -> {
                analyseVariable(code, scopeStructs, mScopeVariables, scopeFunctions)
                mScopeVariables.add(code)
            }
            is AssignmentStatement -> analyseAssignment(code,  mScopeVariables, scopeFunctions)
            is ReturnStatement -> analyseReturnStatement(code, mScopeVariables, scopeFunctions)
        }
    }

    private fun analyseReturnStatement(
        returnStatement: ReturnStatement,
        scopeVariables: List<VariableDefinition>,
        scopeFunctions: List<FunctionDefinition>
    ) = analyseExpression(returnStatement.expression, scopeVariables, scopeFunctions)

    private fun analyseAssignment(
        assignment: AssignmentStatement,
        scopeVariables: List<VariableDefinition>,
        scopeFunctions: List<FunctionDefinition>
    ) {
        analyseVariableCall(assignment.variableCall, scopeVariables, scopeFunctions)
        analyseExpression(assignment.expression, scopeVariables, scopeFunctions)
        if (assignment.variableCall.returnType != assignment.expression.returnType) throw TODO("TypeMissMatch")
    }

    private fun analyseExpression(expression: Expression, scopeVariables: List<VariableDefinition>, scopeFunctions: List<FunctionDefinition>): Unit = when (expression) {
        is VariableCall -> analyseVariableCall(expression, scopeVariables, scopeFunctions)
        is FunctionCall -> analyseFunctionCall(expression, scopeVariables, scopeFunctions)
        else -> Unit
    }

    private fun analyseVariableCall(variableCall: VariableCall, scopeVariables: List<VariableDefinition>, scopeFunctions: List<FunctionDefinition>) {
        if (variableCall !is VariableCallImpl) throw TODO("huh?")
        if (variableCall.parent != null) {
            analyseExpression(variableCall.parent!!, scopeVariables, scopeFunctions)
            val type = variableCall.parent!!.returnType.typeDefinition
            if (type !is StructDefinition) throw TODO("UnknownRef")
            variableCall.variableDefinition = type.variableDefinitions.children.find { it.name == variableCall.name } ?: throw TODO()
        } else {
            variableCall.variableDefinition = scopeVariables.find { it.name == variableCall.name } ?: throw TODO()
        }
    }

    private fun analyseFunctionCall(functionCall: FunctionCall, scopeVariables: List<VariableDefinition>, scopeFunctions: List<FunctionDefinition>) {
        if (functionCall !is FunctionCallImpl) throw TODO("huh?")
        functionCall.functionDefinition = scopeFunctions.find { it.name == functionCall.name } ?: throw TODO("UnknownRef")
        functionCall.arguments.children.forEach { analyseExpression(it, scopeVariables, scopeFunctions) }
    }

}
