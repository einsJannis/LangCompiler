import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.31"
}

group = "dev.einsjannis"
version = "0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        optIn("kotlin.contracts.ExperimentalContracts")
        optIn("kotlin.ExperimentalStdlibApi")
    }
}

fun org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions.optIn(annotation: String) {
    freeCompilerArgs += "-Xopt-in=$annotation"
}
