
enablePlugins(ScalaJSPlugin)

enablePlugins(ScalaJSBundlerPlugin)

name := "grpc-sjs-client"

organization := "org.akka-js"

scalaVersion := "2.12.4"

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)

scalaJSUseMainModuleInitializer := true

libraryDependencies ++= Seq(
  "com.thesamet.scalapb" %%% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion,
  "com.thesamet.scalapb" %%% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf"
)

npmDependencies in Compile ++= Seq(
  "grpc" -> "1.10.1"
)

skip in packageJSDependencies := false
