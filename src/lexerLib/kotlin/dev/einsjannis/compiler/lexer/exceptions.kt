package dev.einsjannis.compiler.lexer

import dev.einsjannis.Position
import java.io.File

class UnknownTokenException(val index: Int, val content: String, val file: File) : RuntimeException() {

    val position: Position by lazy {
        val string = content.substring(0..index)
        val x = index - string.lastIndexOf('\n')
        val y = string.count { it == '\n' }
        Position(x, y)
    }

    val row: Int get() = position.y

    val column: Int get() = position.x

    val context: String by lazy {
        content.substring(index).let { it.substring(0, it.indexOf(' ')) }
    }

    override val message: String
        get() = "Unknown token found at [$row:$column] : \"$context\" : File(${file.absolutePath})"

}
