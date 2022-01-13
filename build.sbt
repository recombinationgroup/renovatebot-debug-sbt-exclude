name := "renovatebot-sbt-example"

scalaVersion := "2.13.8"

libraryDependencies ++= Seq(
      "com.lightbend.akka" %% "akka-stream-alpakka-csv" % "2.0.0" excludeAll ExclusionRule(organization = "com.typesafe.akka"),
      "com.lightbend.akka" %% "akka-stream-alpakka-s3" % "2.0.0",
)
