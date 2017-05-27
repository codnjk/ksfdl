import org.gradle.api.tasks.bundling.Compression
import org.gradle.api.tasks.bundling.Tar

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.1.2-2" apply false
    id("application")
}

allprojects {
    apply {
        plugin("kotlin")
    }
    repositories {
        gradleScriptKotlin()
        jcenter()
    }

    dependencies {
        compile(kotlinModule("stdlib"))

        testCompile(kotlinModule("reflect"))
        testCompile("junit:junit:4.12")
        testCompile("com.natpryce:hamkrest:1.4.0.0")
        testCompile("com.nhaarman:mockito-kotlin:1.4.0")
    }
}

dependencies {
    testCompile(project(":fake-ftp-server"))
}

configure<ApplicationPluginConvention> {
    mainClassName = "ksfdl.CliKt"
}

val distTar: Tar by tasks
with(distTar) {
    compression = Compression.BZIP2
}
