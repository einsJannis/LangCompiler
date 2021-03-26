package dev.einsjannis.lang.compiler

import dev.einsjannis.lang.compiler.parser.parse
import dev.einsjannis.lang.compiler.semanticAnalysis.SemanticAnalyser
import java.io.File

public object Compiler {

    public fun compile(inputFile: File, outputDirectory: File): Unit {
        val tokens = lex(inputFile)
        val tree = parse(tokens)
        SemanticAnalyser.analyse(tree)
    }

}
