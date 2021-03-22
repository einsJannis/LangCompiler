package dev.einsjannis.lang.compiler.parser

import dev.einsjannis.compiler.parser.FileParser
import dev.einsjannis.lang.compiler.lex
import java.io.File

private val parser = FileParser(DefinitionScope)

fun parse(file: File): DefinitionScope {
    val tokens = lex(file)
    return parser.parse(tokens)
}
