plugins {
    id("java")
    application
    checkstyle
    jacoco
    id("com.diffplug.spotless") version "7.0.0"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass = "hexlet.code.App"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:7.0.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.18.0")
    compileOnly("org.projectlombok:lombok:1.18.34")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.0")
    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports { xml.required.set(true) }
}
spotless {
    java {
        removeUnusedImports()
        googleJavaFormat("1.17.0")
            .aosp()
            .reflowLongStrings()
    }
}