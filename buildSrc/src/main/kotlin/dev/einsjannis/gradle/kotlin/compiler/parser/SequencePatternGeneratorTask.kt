package dev.einsjannis.gradle.kotlin.compiler.parser

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.work.InputChanges
import java.io.File
import dev.einsjannis.gradle.kotlin.template

abstract class SequencePatternGeneratorTask : DefaultTask() {

    @get:OutputDirectory
    abstract val outputDirectory: DirectoryProperty

    @get:Input
    abstract val amountOfSequencePatterns: Property<Int>

    @TaskAction
    fun execute(inputChanges: InputChanges) {
        val file = File("${outputDirectory.get().asFile.path}/dev/einsjannis/compiler/parser/generatedPatterns.kt")
        if (!file.parentFile.exists()) file.parentFile.mkdirs()
        if (!file.exists()) file.createNewFile()
        val content = generate()
        file.writeText(content)
    }

    //language=kotlin
    private fun generate(): String = """
        package dev.einsjannis.compiler.parser

        import dev.einsjannis.*
        import dev.einsjannis.plus
        import dev.einsjannis.compiler.lexer.*
        ${generateFuns()}
    """.trimIndent()

    private fun generateFuns(): String = template(amountOfSequencePatterns.get()) { generateSequencePattern(it) }

    //language=kotlin
    private fun generateSequencePattern(index: Int) = """
        fun <${generateGenericsDef(index)}> sequence$index(patterns: Tuple$index<${generateGenericsPattern(index)}>, constructor: (Tuple$index<${generateGenerics(index)}>) -> N) = object : Pattern<N> {

            @Suppress("UNCHECKED_CAST")
            override fun match(tokens: AdvancedIterator<Token>): Match<N> {
                tokens.pushContext()
                var tuple: Tuple<Node?> = tupleOf()
                for (pattern in patterns) {
                    val match = tokens.match(pattern)
                    if (match is NoMatch) {
                        tokens.popContext()
                        return NoMatch(NoMatch.Cause.PatternMissMatch(pattern, match.cause))
                    }
                    tuple = tuple.plus(match.node)
                }
                tokens.clearContext()
                return ValidMatch(constructor(tuple as Tuple$index<${generateGenerics(index)}>))
            }

        }
    """

    private fun generateGenericsDef(index: Int) = template(index, ", ") { "T$it : Node?" }.let {
        if (it == "") "N : Node" else "N : Node, $it"
    }

    private fun generateGenerics(index: Int) = template(index, ", ") { "T$it" }.let {
        if (it == "") "Node?" else "Node?, $it"
    }

    private fun generateGenericsPattern(index: Int) = template(index, ", ") { "Pattern<T$it>" }.let {
        if (it == "") "Pattern<*>" else "Pattern<*>, $it"
    }

}
