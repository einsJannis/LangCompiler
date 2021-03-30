package dev.einsjannis.compiler.parser

/*fun Node?.printTree(indent: String = "") {
    if (this == null) {
        println("${indent}null"); return
    }
    println(indent + this::class.simpleName)
    forEach { it.printTree("$indent  ") }
}*/

val NoMatch.Cause.info: String
    get() {
        val builder = StringBuilder()
        info(builder)
        return builder.toString()
    }

fun NoMatch.Cause.info(stringBuilder: StringBuilder, indent: String = "") {
    when (this) {
        is NoMatch.Cause.PatternMissMatch   -> {
            stringBuilder.appendLine("$indent${this.expected.name}")
            this.cause.info(stringBuilder, "$indent  ")
        }
        is NoMatch.Cause.PatternMissMatches -> this.forEach { it.info(stringBuilder, indent) }
        is NoMatch.Cause.TokenMissMatch     -> stringBuilder.appendLine("${indent}expected: ${this.expected.name}; found: ${this.found.type.name} ${this.found.index}")
        is NoMatch.Cause.NoTokensLeft       -> stringBuilder.appendLine("${indent}expected: ${this.expected.name}; no tokens left")
    }
}

fun NoMatch.Cause.printTree(): Unit = println(info)

val Pattern<*>.name
    get() = if (this is TokenPattern) {
        this.tokenType.name
    } else {
        this::class.simpleName
    }
