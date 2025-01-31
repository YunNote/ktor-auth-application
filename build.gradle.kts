import io.ktor.plugin.*

val kotlin_version: String by project
val logback_version: String by project
val koin_version: String by project

plugins {
    kotlin("jvm") version "2.0.20"
    id("io.ktor.plugin") version "2.3.12"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.10"
}

group = "com.auth"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-config-yaml")
    testImplementation("io.ktor:ktor-server-test-host")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    // https://mvnrepository.com/artifact/io.ktor/ktor-server-content-negotiation-jvm
    implementation("io.ktor:ktor-server-content-negotiation:$KTOR_VERSION")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$KTOR_VERSION")

    implementation("io.ktor:ktor-server-auth:$KTOR_VERSION")
    implementation("io.ktor:ktor-server-auth-jwt:$KTOR_VERSION")

    // Error Handler
    implementation("io.ktor:ktor-server-status-pages:$KTOR_VERSION")

    // Koin
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")

}
