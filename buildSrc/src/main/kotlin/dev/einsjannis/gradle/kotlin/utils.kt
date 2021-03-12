package dev.einsjannis.gradle.kotlin

import org.gradle.api.tasks.SourceSet

fun SourceSet.dependsOn(other: SourceSet) {
    this.compileClasspath += other.output
    this.runtimeClasspath += other.output
}
