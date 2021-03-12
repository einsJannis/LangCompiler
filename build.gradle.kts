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
    val main by getting {
        dependsOn(lexerLib)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit"))
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

}

fun org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions.optIn(annotation: String) {
    freeCompilerArgs += "-Xopt-in=$annotation"
}
