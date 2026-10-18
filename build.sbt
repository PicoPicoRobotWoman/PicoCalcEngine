organization := "pico.calc.framework"
version      := "1.0.0"
scalaVersion := "3.9.0"

lazy val compilerModule = project
  .in(file("compiler"))
  .settings(
    name := "compiler",
    libraryDependencies +=
      "org.scala-lang" %% "scala3-compiler" % scalaVersion.value
  )

lazy val apiModule = project
  .in(file("api"))
  .settings(
    name := "api"
  )

lazy val literalsModule = project
  .in(file("literals"))
  .dependsOn(apiModule)
  .settings(
    name := "literals"
  )

lazy val functionsModule = project
  .in(file("functions"))
  .dependsOn(apiModule)
  .settings(
    name := "functions"
  )

lazy val constantsModule = project
  .in(file("constants"))
  .dependsOn(apiModule)
  .settings(
    name := "constants"
  )

lazy val coreModule = project
  .in(file("core"))
  .dependsOn(apiModule, compilerModule, literalsModule, functionsModule, constantsModule)
  .settings(
    name := "core"
  )

lazy val exampleModule = project
  .in(file("example"))
  .dependsOn(compilerModule)
  .settings(
    name := "example"
  )

lazy val root = project
  .in(file("."))
  .aggregate(compilerModule, exampleModule, apiModule)
  .settings(
    name := "pico-calc-engine",
    publish / skip := true
  )


