package dev.einsjannis.compiler.lexer

val Token.serialized get() = "${this.type.name}(${this.content}) in \"${this.file.absolutePath}\" at index ${this.index}"
