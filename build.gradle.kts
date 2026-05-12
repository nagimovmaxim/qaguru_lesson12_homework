plugins {
    id("java")
    id("io.qameta.allure") version "2.12.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

allure {
    version.set("2.34.0")
    adapter {
        aspectjVersion.set("1.9.22")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation(platform("io.qameta.allure:allure-bom:2.34.0"))
    testImplementation(platform("org.assertj:assertj-bom:3.27.6"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.codeborne:selenide:7.3.3")
    testImplementation("org.assertj:assertj-core")
    testImplementation("io.qameta.allure:allure-selenide")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("org.aspectj:aspectjweaver:1.9.22")
}

tasks.test {
    doFirst {
        delete("${buildDir}/allure-results")
    }
    useJUnitPlatform()
    systemProperties(System.getProperties().map { it.key.toString() to it.value }.toMap())
    testLogging {
        lifecycle {
            events( "started", "skipped", "failed", "standard_error", "standard_out")
            setExceptionFormat( "short")
        }
    }
}