
ThisBuild / scalaVersion     := "2.11.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "Spark Fireworks"
ThisBuild / organizationName := "Spark Fireworks"

lazy val dependencies = Seq(
  // https://mvnrepository.com/artifact/org.scalatest/scalatest
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  // https://mvnrepository.com/artifact/org.apache.spark/spark-hive
  "org.apache.spark" %% "spark-hive" % "2.3.0" % "provided",
  // https://mvnrepository.com/artifact/org.apache.spark/spark-core
  "org.apache.spark" %% "spark-core" % "2.3.0",
  // https://mvnrepository.com/artifact/org.apache.spark/spark-sql
  "org.apache.spark" %% "spark-sql" % "2.3.0",
  // https://mvnrepository.com/artifact/com.holdenkarau/spark-testing-base
  "com.holdenkarau" %% "spark-testing-base" % "2.3.0_0.12.0" % "test",
  // https://mvnrepository.com/artifact/com.databricks/spark-xml
  "com.databricks" %% "spark-xml" % "0.4.1"

)

lazy val root = (project in file("."))
  .settings(
    mainClass in (Compile, packageBin) := Some(""),
    name := "",
    libraryDependencies ++= dependencies
  )

// Simple and constant jar name
assemblyJarName in assembly := s"spark-fireworks.jar"
