import sbt._

name := "newman-example"

organization := "com.github.arschles"

scalaVersion := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-feature")

scalacOptions in Test ++= Seq("-Yrangepos")


libraryDependencies ++= {
  Seq(
  	"com.stackmob" %% "newman" % "1.3.5" exclude("org.scala-lang", "scala-compiler")
  )
}

testOptions in Test += Tests.Argument("html", "console")

conflictManager := ConflictManager.strict

dependencyOverrides <+= (scalaVersion) { vsn => "org.scala-lang" % "scala-library" % vsn }

logBuffered := false
