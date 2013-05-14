
import sbtrelease._
import ReleaseStateTransformations._

import GeodudeBuild._
import Dependencies._

name := "geodude"

organization := "com.github.hjast"

version := "0.2.0"

scalaVersion := "2.10.0"

resolvers += Resolver.sonatypeRepo("releases")

scalacOptions ++= Seq(
                      "-feature",
                      "-language:higherKinds",
                      "-language:implicitConversions",
                      "-deprecation",
                      "-unchecked"
                    )
		   	
publishMavenStyle := true
					
publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { x => false }

licenses := Seq("BSD-style" -> url("http://www.opensource.org/licenses/bsd-license.php"))

homepage := Some(url("http://github.com/hjast/geodude"))
			
pomExtra := (
  <parent>
      <groupId>org.sonatype.oss</groupId>
      <artifactId>oss-parent</artifactId>
      <version>7</version>
    </parent>
  <scm>
    <url>git@github.com:hjast/geodude.git</url>
    <connection>scm:git:git@github.com:hjast/geodude.git</connection>
  </scm>
  <developers>
    <developer>
      <id>reubendoetsch</id>
      <name>Reuben Doetsch</name>
      <url>http://reubendoetsch.com</url>
    </developer>
  </developers>)

					
					
