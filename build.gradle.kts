import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(files("custom/firebird-1.0.jar"))
    implementation("io.javalin:javalin:6.0.0")
    implementation("io.javalin.community.ssl:ssl-plugin:6.0.0")
    implementation("org.slf4j:slf4j-simple:2.0.11")
    implementation("org.commonmark:commonmark:0.21.0")
    implementation(kotlin("script-runtime"))
    implementation("com.github.spullara.mustache.java:compiler:0.9.10")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0-rc1")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.8.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("MainKt")
}

tasks.register<JavaExec>("runLocal") {
    group = "tsl"
    description = "run the project on my local machine, not in the vm"

    mainClass.set("MainKt")
    classpath = sourceSets["main"].runtimeClasspath
    environment(
        Pair("DEBUG", "true"),
        Pair("PORT", 1025)
    )
}

tasks.register<JavaExec>("runServer") {
    group = "tsl"
    description = "run the project on the vm on the server, not on my local machine"

    mainClass.set("MainKt")
    classpath = sourceSets["main"].runtimeClasspath
    environment(
        Pair("DEBUG", "false"),
        Pair("PORT", 443)
    )
}