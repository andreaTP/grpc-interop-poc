addSbtPlugin("com.thesamet" % "sbt-protoc" % "0.99.18" exclude ("com.thesamet.scalapb", "protoc-bridge_2.10"))

libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin-shaded" % "0.7.4"

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.22")

addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.11.0")
