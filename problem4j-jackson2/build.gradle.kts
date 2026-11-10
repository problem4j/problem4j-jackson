plugins {
    id("internal.java-library-convention")
    id("internal.mrjar-module-info-convention")
    id("internal.publishing-convention")
    alias(libs.plugins.nmcp)
}

dependencies {
    // Main
    compileOnly(libs.jspecify)
    compileOnly(libs.problem4j.core)
    compileOnly(libs.jackson2.databind)
    compileOnly(libs.jackson2.dataformat.xml)

    // Test
    testImplementation(libs.jspecify)
    testImplementation(libs.problem4j.core)
    testImplementation(libs.jackson2.databind)
    testImplementation(libs.jackson2.dataformat.xml)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)

    errorprone(libs.errorprone.core)
    errorprone(libs.nullaway)
}

// see build-logic/src/main/kotlin/internal.publishing-convention.gradle.kts
internalBuild {
    displayName = "Problem4J Jackson2"
    description = "Jackson2 integration for library implementing RFC7807 (and RFC9457)"
}

val javaTargetVersion = 8

tasks.named<JavaCompile>("compileJava") {
    options.release = javaTargetVersion
}

tasks.withType<Javadoc>().configureEach {
    javadocTool =
        javaToolchains.javadocToolFor {
            languageVersion = JavaLanguageVersion.of(javaTargetVersion)
        }
}

tasks.withType<Jar>().configureEach {
    manifest {
    }
}
