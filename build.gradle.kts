import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.10"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.javalin:javalin:6.0.0")
    implementation("io.javalin.community.ssl:ssl-plugin:6.0.0")
    implementation("org.slf4j:slf4j-simple:2.0.11")
    implementation("org.commonmark:commonmark:0.21.0")
    implementation(kotlin("script-runtime"))
    implementation("com.github.spullara.mustache.java:compiler:0.9.10")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0-rc1")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}
