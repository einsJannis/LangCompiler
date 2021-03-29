package dev.einsjannis.lang.compiler.ir

fun DefinitionScope.printInfo(indent: kotlin.String = "") {
    println("${indent}Definitions {")
    children.forEach { when (it) {
        is StructDefinition -> it.printInfo("$indent  ")
        is VariableDefinition -> it.printInfo("$indent  ")
        is FunctionDefinition -> it.printInfo("$indent  ")
    } }
    println("$indent}")
}

fun StructDefinition.printInfo(indent: kotlin.String = "") {
    println("${indent}Struct {")
    println("${indent}  name = $name")
    variableDefinitions.printInfo("$indent  ")
}

fun StructVariableDefinitionScope.printInfo(indent: kotlin.String = "") {
    println("${indent}Variables {")
    children.forEach { it.printInfo("$indent  ") }
}

fun VariableDefinition.printInfo(indent: kotlin.String = "") {
    println("${indent}Variable {")
    println("${indent}  name = $name")
    returnType.printInfo("$indent  ")
    initialization?.let {
        println("${indent}  Initialization {")
        it.printInfo("$indent    ")
        println("}")
    }
}

fun ReturnType.printInfo(indent: kotlin.String = "") {
    println("${indent}ReturnType {")
    println("${indent}  refName = ${typeDefinition.name}")
    println("${indent}  isPointer = ${isPointer}")
    println("${indent}}")
}

fun Expression.printInfo(indent: kotlin.String = ""): Unit = when (this) {
    is FunctionCall -> {
        println("${indent}FunctionCall {")
        println("${indent}  refName = ${functionDefinition.name}")
        arguments.printInfo("$indent  ")
        returnType.printInfo("$indent  ")
        println("${indent}}")
    }
    is VariableCall -> {
        println("${indent}VariableCall {")
        println("${indent}  refName = ${variableDefinition.name}")
        parent?.let { it.printInfo("$indent  ") }
        returnType.printInfo("$indent  ")
        println("${indent}}")
    }
    is Number -> {
        println("${indent}Number {")
        println("${indent}  value = $bytes")
        returnType.printInfo("$indent  ")
        println("${indent}}")
    }
    is Char -> {
        println("${indent}Char {")
        println("${indent}  value = $value")
        returnType.printInfo("$indent  ")
        println("${indent}}")
    }
    is String -> {
        println("${indent}String {")
        println("${indent}  value = $value")
        returnType.printInfo("$indent  ")
        println("${indent}}")
    }
    is Boolean -> {
        println("${indent}Boolean {")
        println("${indent}  value = $value")
        returnType.printInfo("$indent  ")
        println("${indent}}")
    }
    is Cast -> {
        println("${indent}Cast {")
        returnType.printInfo("$indent  ")
        expression.printInfo("$indent  ")
    }
    else -> TODO("huh?")
}

fun FunctionDefinition.printInfo(indent: kotlin.String = "") {
    println("${indent}Function {")
    println("${indent}  name = $name")
    arguments.printInfo("$indent  ")
    returnType.printInfo("$indent  ")
    if (this is FunctionImplementationDefinition) {
        code.printInfo("$indent  ")
    }
    println("}")
}

fun ArgumentDefinitionScope.printInfo(indent: kotlin.String) {
    println("${indent}Arguments {")
    children.forEach { it.printInfo("$indent  ") }
    println("$indent}")
}

fun ArgumentScope.printInfo(indent: kotlin.String) {
    println("${indent}Arguments {")
    children.forEach { it.printInfo("$indent  ") }
    println("${indent}}")
}

fun CodeScope.printInfo(indent: kotlin.String) {
    println("${indent}Code {")
    children.forEach {
        when (it) {
            is Expression -> it.printInfo("$indent  ")
            is AssignmentStatement -> it.printInfo("$indent  ")
            is ReturnStatement -> it.printInfo("$indent  ")
        }
    }
    println("${indent}}")
}

fun AssignmentStatement.printInfo(indent: kotlin.String) {
    println("${indent}Assignment {")
    variableCall.printInfo("$indent  ")
    expression.printInfo("$indent  ")
    println("$indent}")
}

fun ReturnStatement.printInfo(indent: kotlin.String) {
    println("${indent}Return {")
    expression.printInfo("$indent  ")
    println("$indent}")
}
