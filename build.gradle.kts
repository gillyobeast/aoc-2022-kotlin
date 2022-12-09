plugins {
    kotlin("jvm") version "1.7.22"
}


dependencies{
    implementation("org.junit.jupiter:junit-jupiter:5.9.1")
}
repositories {
    mavenCentral()
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }

    wrapper {
        gradleVersion = "7.6"
    }
}
