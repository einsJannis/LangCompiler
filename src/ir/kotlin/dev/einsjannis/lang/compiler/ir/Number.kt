package dev.einsjannis.lang.compiler.ir

import dev.einsjannis.lang.compiler.ir.builtin.Types
import dev.einsjannis.lang.compiler.ir.internal.ReturnTypeImpl

interface Number : Primitive {

    override val bytes: ByteArray

    override val returnType: ReturnType
        get() = ReturnTypeImpl(when {
            bytes.size > 64 -> Types.Blob.from(bytes.size)
            bytes.size > 32 -> Types.Long
            bytes.size > 8 -> Types.Integer
            else -> Types.Byte
        }, false)

}
