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
        variableDefinition.initialization?.let {
            analyseExpression(it, scopeStructs, scopeVariables, scopeFunctions)
            if (it.returnType != variableDefinition.returnType) throw TODO("type miss match")
        }
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
        functionDefinition.code.children.forEach { analyseCode(it, scopeStructs, mScopeVariables, listOf(functionDefinition).plus(scopeFunctions), functionDefinition) }
    }

    private fun analyseArgument(
        argumentDefinition: ArgumentDefinition,
        scopeStructs: List<TypeDefinition>,
        scopeVariables: List<VariableDefinition>
    ) {
        analyseIdentifier(argumentDefinition.name, scopeVariables)
        analyseReturnType(argumentDefinition.returnType, scopeStructs)
    }

    private fun analyseCode(code: Code, scopeStructs: List<TypeDefinition>, scopeVariables: List<VariableDefinition>, scopeFunctions: List<FunctionDefinition>, enclosingFunction: FunctionDefinition) {
        val mScopeVariables: MutableList<VariableDefinition> = scopeVariables.toMutableList()
        when (code) {
            is Expression -> analyseExpression(code, scopeStructs, mScopeVariables, scopeFunctions)
            is ConditionStatement -> analyseConditionStatement(code, scopeStructs, mScopeVariables, scopeFunctions, enclosingFunction)
            is VariableDefinition -> {
                analyseVariable(code, scopeStructs, mScopeVariables, scopeFunctions)
                mScopeVariables.add(code)
            }
            is AssignmentStatement -> analyseAssignment(code, scopeStructs, mScopeVariables, scopeFunctions)
            is ReturnStatement -> analyseReturnStatement(code, scopeStructs, mScopeVariables, scopeFunctions, enclosingFunction)
        }
    }

    private fun analyseConditionStatement(
        conditionStatement: ConditionStatement,
        scopeStructs: List<TypeDefinition>,
        scopeVariables: List<VariableDefinition>,
        scopeFunctions: List<FunctionDefinition>,
        enclosingFunction: FunctionDefinition
    ) {
        analyseExpression(conditionStatement.condition, scopeStructs, scopeVariables, scopeFunctions)
        conditionStatement.code.children.forEach {
            analyseCode(it, scopeStructs, scopeVariables, scopeFunctions, enclosingFunction)
        }
    }

    private fun analyseReturnStatement(
        returnStatement: ReturnStatement,
        scopeStructs: List<TypeDefinition>,
        scopeVariables: List<VariableDefinition>,
        scopeFunctions: List<FunctionDefinition>,
        enclosingFunction: FunctionDefinition
    ) {
        analyseExpression(returnStatement.expression, scopeStructs, scopeVariables, scopeFunctions)
        if (enclosingFunction.returnType != returnStatement.expression.returnType) throw TODO("type miss match")
    }

    private fun analyseAssignment(
        assignment: AssignmentStatement,
        scopeStructs: List<TypeDefinition>,
        scopeVariables: List<VariableDefinition>,
        scopeFunctions: List<FunctionDefinition>
    ) {
        analyseVariableCall(assignment.variableCall, scopeStructs, scopeVariables, scopeFunctions)
        analyseExpression(assignment.expression, scopeStructs, scopeVariables, scopeFunctions)
        if (assignment.variableCall.returnType != assignment.expression.returnType) throw TODO("TypeMissMatch")
    }

    private fun analyseExpression(expression: Expression, scopeStructs: List<TypeDefinition>, scopeVariables: List<VariableDefinition>, scopeFunctions: List<FunctionDefinition>): Unit = when (expression) {
        is VariableCall -> analyseVariableCall(expression, scopeStructs, scopeVariables, scopeFunctions)
        is FunctionCall -> analyseFunctionCall(expression, scopeStructs, scopeVariables, scopeFunctions)
        is Cast -> analyseCast(expression, scopeStructs, scopeVariables, scopeFunctions)
        else -> Unit
    }

    private fun analyseCast(
        cast: Cast,
        scopeStructs: List<TypeDefinition>,
        scopeVariables: List<VariableDefinition>,
        scopeFunctions: List<FunctionDefinition>
    ) {
        analyseExpression(cast.expression, scopeStructs, scopeVariables, scopeFunctions)
        analyseReturnType(cast.returnType, scopeStructs)
    }

    private fun analyseVariableCall(variableCall: VariableCall, scopeStructs: List<TypeDefinition>, scopeVariables: List<VariableDefinition>, scopeFunctions: List<FunctionDefinition>) {
        if (variableCall !is VariableCallImpl) throw TODO("huh?")
        if (variableCall.parent != null) {
            analyseExpression(variableCall.parent!!, scopeStructs, scopeVariables, scopeFunctions)
            val type = variableCall.parent!!.returnType.typeDefinition
            if (type !is StructDefinition) throw TODO("UnknownRef")
            variableCall.variableDefinition = type.variableDefinitions.children.find { it.name == variableCall.name } ?: throw TODO()
        } else {
            variableCall.variableDefinition = scopeVariables.find { it.name == variableCall.name } ?: throw TODO()
        }
    }

    private fun analyseFunctionCall(functionCall: FunctionCall, scopeStructs: List<TypeDefinition>, scopeVariables: List<VariableDefinition>, scopeFunctions: List<FunctionDefinition>) {
        if (functionCall !is FunctionCallImpl) throw TODO("huh?")
        functionCall.functionDefinition = scopeFunctions.find { it.name == functionCall.name } ?: throw TODO("UnknownRef")
        functionCall.arguments.children.forEach { analyseExpression(it, scopeStructs, scopeVariables, scopeFunctions) }
    }

}
