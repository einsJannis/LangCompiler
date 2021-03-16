package dev.einsjannis.compiler.parser

interface Node : Iterable<Node?> {

    var parent: Node?

}
