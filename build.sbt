ThisBuild / organization := "pico.calc.framework"
ThisBuild / version      := "1.0.0"
ThisBuild / scalaVersion := "3.9.0"

ThisBuild / description := "A Scala-based expression evaluation engine"

lazy val commonSettings = Seq(
  // Scala compiler
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Wunused:all"
  ),

  // Java compiler
  javacOptions ++= Seq(
    "-encoding", "UTF-8",
    "--release", "21",
    "-Xlint:all"
  ),

  // JVM options for forked run / test processes
  Compile / run / fork := true,
  Test / fork := true,

  Compile / run / javaOptions ++= Seq(
    "-Dfile.encoding=UTF-8"
  ),

  Test / javaOptions ++= Seq(
    "-Dfile.encoding=UTF-8"
  )
)

ThisBuild / homepage := Some(
  uri("https://github.com/PicoPicoRobotWoman/PicoCalcEngine")
)

ThisBuild / licenses := Seq(
  "Apache-2.0" -> uri("https://www.apache.org/licenses/LICENSE-2.0.txt")
)

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
  .dependsOn(apiModule, compilerModule)
  .settings(
    name := "core"
  )

lazy val exampleModule = project
  .in(file("example"))
  .dependsOn(compilerModule, coreModule, apiModule, literalsModule, functionsModule, constantsModule)
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
