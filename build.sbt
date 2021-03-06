
name := "BIDMach"

version := "0.1.0"

organization := "edu.berkeley.bid"

scalaVersion := "2.9.1"

resolvers ++= Seq(
  "Scala Tools Snapshots" at "http://scala-tools.org/repo-snapshots/"
)

libraryDependencies <<= (scalaVersion, libraryDependencies) { (sv, deps) =>
  deps :+ ("org.scala-lang" % "scala-compiler" % sv)
}

libraryDependencies += "org.scala-lang" % "jline" % "2.9.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.8" % "test"

libraryDependencies += "org.scala-tools.testing" %% "scalacheck" % "1.9" % "test"

libraryDependencies += "junit" % "junit" % "4.5" % "test"

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

javacOptions ++= Seq("-source", "1.5", "-target", "1.5")

scalacOptions ++= Seq("-deprecation","-target:jvm-1.5")

initialCommands := scala.io.Source.fromFile("lib/bidmach_init.scala").getLines.mkString("\n")

javaOptions += "-Xmx12g"

seq(ProguardPlugin.proguardSettings :_*)

proguardOptions ++= Seq (
  "-keep class scala.** { *; }",
  "-keep class org.jfree.** { *; }",
  keepMain("scala.tools.nsc.MainGenericRunner"),
  keepLimitedSerializability,
  keepAllScala,
  "-keep class ch.epfl.** { *; }",
  "-keep interface scala.ScalaObject"
)


