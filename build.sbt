scalaVersion := "2.12.4"
name := "att_gui"
organization := "arena.com"
version := "1.0"


libraryDependencies += "org.typelevel" %% "cats-core" % "1.0.1"
libraryDependencies += "net.liftweb" %% "lift-json" % "3.0.1"
libraryDependencies += "org.controlsfx" % "controlsfx" % "8.40.14"

libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.144-R12"
 scalacOptions ++= Seq("-unchecked", "-deprecation", "-Xcheckinit", "-encoding", "utf8", "-feature")

//unmanagedJars in Compile += Attributed.blank(file("/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/jfxrt.jar"))
  unmanagedJars in Compile += Attributed.blank(file("/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/jfxrt.jar"))

fork := true


