import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("war")
    kotlin("jvm") version "1.5.10"
    kotlin("plugin.spring") version "1.5.10"
}

group = "com.tkapp"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

tasks.withType<War> {
    enabled = true
    archiveName = "vm-sales.war"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-web-services")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.data:spring-data-rest-hal-explorer")
    implementation("org.springframework.session:spring-session-core")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:1.3.0")
    implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:1.3.0")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("mysql:mysql-connector-java")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    runtimeOnly("org.springframework.boot:spring-boot-starter-tomcat")
    implementation("org.apache.poi:poi:4.1.2")
    implementation("org.apache.poi:poi-ooxml:4.1.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

/** -----------------------------------------------------
 * Mybatis Generator 実行タスク
 * -----------------------------------------------------*/
val mybatisMapperVersion = "4.1.5"
val mybatisGeneratorVersion = "1.4.0"
val postgresqlConnectorJavaVersion = "42.1.4"

val mybatisGenerator by configurations.creating

dependencies {
    mybatisGenerator(group="org.mybatis.generator", name="mybatis-generator-core", version=mybatisGeneratorVersion)
    mybatisGenerator(group="org.postgresql", name="postgresql", version=postgresqlConnectorJavaVersion)
    mybatisGenerator(group="tk.mybatis", name="mapper", version=mybatisMapperVersion)
}
task("mybatisGenerator") {
    doLast {
        ant.withGroovyBuilder {
            "taskdef"("name" to "mbgenerator", "classname" to "org.mybatis.generator.ant.GeneratorAntTask", "classpath" to mybatisGenerator.asPath)
        }
        ant.withGroovyBuilder {
            "mbgenerator"("overwrite" to true, "configfile" to "/Users/userpath/IdeaProjects/CigarettesInventoryControl/src/main/resources/mybatis/generatorConfig.xml", "verbose" to true)
        }
    }
}