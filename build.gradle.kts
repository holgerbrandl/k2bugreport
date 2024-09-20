plugins {
    kotlin("jvm") version "2.0.10"
}

group = "bug.report"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.holgerbrandl:kalasim:0.12.107")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}