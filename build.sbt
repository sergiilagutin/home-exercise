ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.18"

lazy val root = (project in file("."))
  .settings(
    name := "test-home-exercise"
  )
  .settings(libraryDependencies ++= Dependencies.all)
