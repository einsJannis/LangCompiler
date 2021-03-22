@file:Suppress("Annotator", "Annotator")

import dev.einsjannis.gradle.kotlin.dependsOn
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.31"
}

group = "dev.einsjannis"
version = "0.1-SNAPSHOT"

sourceSets {
    val privateLib by creating {
        java {
            srcDir("src/privateLib/generated")
        }
    }
    val lexerLib by creating {
        dependsOn(privateLib)
    }
    val parserLib by creating {
        dependsOn(privateLib)
        dependsOn(lexerLib)
        java {
            srcDir("src/parserLib/generated")
        }
    }
    val lexerImpl by creating {
        dependsOn(lexerLib)
    }
    val parserImpl by creating {
        dependsOn(privateLib)
        dependsOn(lexerLib)
        dependsOn(lexerImpl)
        dependsOn(parserLib)
    }
    @kotlin.Suppress("UNUSED")
    val main by getting {
        dependsOn(privateLib)
        dependsOn(lexerLib)
        dependsOn(lexerImpl)
        dependsOn(parserLib)
        dependsOn(parserImpl)
    }
    @kotlin.Suppress("UNUSED")
    val test by getting {
        dependsOn(privateLib)
        dependsOn(lexerLib)
        dependsOn(lexerImpl)
        dependsOn(parserLib)
        dependsOn(parserImpl)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit"))
}

kotlin {
    explicitApi()
}

tasks {

    test {
        useJUnit()
    }

    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            optIn("kotlin.contracts.ExperimentalContracts")
            optIn("kotlin.ExperimentalStdlibApi")
        }
    }

    register<dev.einsjannis.gradle.kotlin.TupleGeneratorTask>("tupleGeneratorTask") {
        outputDirectory.set(file("src/privateLib/generated"))
        amountOfTuples.set(16)
    }

    register<dev.einsjannis.gradle.kotlin.compiler.parser.SequencePatternGeneratorTask>("sequencePatternTask") {
        outputDirectory.set(file("src/parserLib/generated"))
        amountOfSequencePatterns.set(16)
    }

}

fun org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions.optIn(annotation: String) {
    freeCompilerArgs = freeCompilerArgs + "-Xopt-in=$annotation"
}
