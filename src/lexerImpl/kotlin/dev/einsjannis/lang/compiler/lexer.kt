package dev.einsjannis.lang.compiler

import dev.einsjannis.compiler.lexer.Lexer
import dev.einsjannis.compiler.lexer.token
import dev.einsjannis.compiler.lexer.tokens
import java.io.File

object Token {
    object Keyword {
        val Function = token(Regex.fromLiteral("fun"))
        val Variable = token(Regex.fromLiteral("var"))
        val If = token(Regex.fromLiteral("if"))
        val Else = token(Regex.fromLiteral("else"))
        val While = token(Regex.fromLiteral("while"))
    }
    object Symbol {
        object Brackets {
            val ParenthesesL = token(Regex.fromLiteral("{"))
            val ParenthesesR = token(Regex.fromLiteral("}"))
            val BracesL = token(Regex.fromLiteral("("))
            val BracesR = token(Regex.fromLiteral(")"))
        }
        object Operator {
            val Plus = token(Regex.fromLiteral("+"))
            val Minus = token(Regex.fromLiteral("-"))
            val And = token(Regex.fromLiteral("&"))
            val Or = token(Regex.fromLiteral("|"))
            val Not = token(Regex.fromLiteral("!"))
            val Equals = token(Regex.fromLiteral("=="))
            val LessThenOrEqual = token(Regex.fromLiteral("<="))
            val LessThen = token(Regex.fromLiteral("<"))
            val GreaterThenOrEqual = token(Regex.fromLiteral(">="))
            val GreaterThen = token(Regex.fromLiteral(">"))
            val Assign = token(Regex.fromLiteral("="))
            val ReturnType = token(Regex.fromLiteral(":"))
        }
        val Colon = token(Regex.fromLiteral(","))
        val SemiColon = token(Regex.fromLiteral(";"))
    }
    object Primitive {
        object Number {
            val NaturalNumber = token(Regex("[0-9]+|0x[0-9a-fA-F]+]|0b[01]+"))
            val RationalNumber = token(Regex("[0-9]*.[0-9]+"))
        }
        val String = token(Regex("\".*\""))
        val Char = token(Regex("\'.\'"))
        val Boolean = token(Regex("true|false"))
    }
    val Identifier = token(Regex("[A-Za-z_][A-Za-z_0-9]*|`.*`"))
}

private val lexer = Lexer(tokens)

fun lex(file: File) = lexer.lex(file)
