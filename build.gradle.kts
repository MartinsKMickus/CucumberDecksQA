plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

dependencies {
    implementation("io.rest-assured:rest-assured:5.4.0")
    implementation("io.cucumber:cucumber-java:7.15.0")
    implementation("io.cucumber:cucumber-junit:7.15.0")

    implementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    implementation("org.junit.vintage:junit-vintage-engine:5.10.2")
    implementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")

    implementation("junit:junit:4.13.2")
}

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}