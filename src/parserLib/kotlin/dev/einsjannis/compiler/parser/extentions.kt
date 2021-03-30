package dev.einsjannis.compiler.parser

/*fun Node?.printTree(indent: String = "") {
    if (this == null) {
        println("${indent}null"); return
    }
    println(indent + this::class.simpleName)
    forEach { it.printTree("$indent  ") }
}*/

fun NoMatch.Cause.printTree(indent: String = ""): Unit = when (this) {
    is NoMatch.Cause.PatternMissMatch -> {
        println("$indent${this.expected.name}")
        this.cause.printTree("$indent  ")
    }
    is NoMatch.Cause.PatternMissMatches -> this.forEach { it.printTree(indent) }
    is NoMatch.Cause.TokenMissMatch -> println("${indent}expected: ${this.expected.name}; found: ${this.found.type.name} ${this.found.index}")
    is NoMatch.Cause.NoTokensLeft -> println("${indent}expected: ${this.expected.name}; no tokens left")
}

val Pattern<*>.name
    get() = if (this is TokenPattern) {
        this.tokenType.name
    } else {
        this::class.simpleName
    }
