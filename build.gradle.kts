buildscript {
  repositories {
    mavenCentral()
    jcenter()
    gradleScriptKotlin()
  }
  dependencies {
    classpath(kotlinModule("gradle-plugin"))
  }
}

apply {
  plugin("kotlin")
}

configure<JavaPluginConvention> {
  setSourceCompatibility(1.7)
  setTargetCompatibility(1.7)
}

repositories {
  gradleScriptKotlin()
}

dependencies {
  compile(kotlinModule("stdlib"))
  testCompile("junit:junit:4.12")
  testCompile("org.assertj:assertj-core:1.7.1")
}
