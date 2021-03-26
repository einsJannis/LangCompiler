package dev.einsjannis.lang.compiler.parser.internal

import dev.einsjannis.compiler.lexer.Token
import dev.einsjannis.lang.compiler.ir.Number

class NumberImpl(token: Token) : Number {

    override val bytes: ByteArray = bytesFromToken(token)

}
