plugins {
    kotlin("jvm") version "1.9.23"
}

group = "com.tyza66.essimple"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib")
    implementation ("org.jetbrains.kotlin:kotlin-reflect")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}