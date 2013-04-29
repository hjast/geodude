import sbt._
import Keys._

// sbt-release plugin
import sbtrelease.ReleasePlugin._
import sbtrelease._
import ReleaseStateTransformations._
import sbtrelease.ReleasePlugin.ReleaseKeys._

object GeodudeBuild extends Build {

    def crossMapped(mappings: (String, String)*): CrossVersion = {
      CrossVersion.binaryMapped(Map(mappings: _*) orElse {
        case v => v
      })
  }

  def CVMapping10010 = crossMapped("2.10.0" -> "2.10")

  def rogueVersion = "2.0.0-beta22"
  def liftVersion = "2.5-RC5"
  def dispatchVersion = "0.8.9"
  
  def databinder(name: String, v: String = dispatchVersion) =
       "net.databinder" %% ("dispatch-" + name) % v withSources() cross CVMapping10010
  
  def dispatchClassicLibs: Seq[sbt.ModuleID]  =
	      Seq(databinder("http"), databinder("oauth"), databinder("json"), databinder("mime"), databinder("http-json"))
  
  def scalaTimeLib = "com.github.nscala-time" %% "nscala-time" % "0.2.0"
  
  def lift(name: String, v: String) =
      "net.liftweb" %% ("lift-%s" format name) % v withSources() exclude("joda-time", "joda-time") cross CVMapping10010

  def liftDeps(v: String = liftVersion) =
      Seq( lift("record",v),  lift("json",v),
         lift("mongodb",v), lift("mongodb-record",v))
		 
  def rogue(name: String, v: String = rogueVersion) =
    "com.foursquare" %% ("rogue-%s" format name) % v intransitive() withSources() cross CVMapping10010

  def rogueLibs  = Seq(rogue("field"), rogue("core"), rogue("lift"))

  def depends: Seq[sbt.ModuleID] = liftDeps() ++ dispatchClassicLibs ++ rogueLibs ++ Seq(scalaTimeLib)
  
  lazy val geodude = Project(
    id = "geodude",
    base = file("."),
    settings = Defaults.defaultSettings ++ releaseSettings ++ 
	 Seq(
        releaseProcess <<= thisProjectRef apply { ref =>
          Seq[ReleaseStep](
            checkSnapshotDependencies,              // : ReleaseStep
            inquireVersions,                        // : ReleaseStep
            runTest,                                // : ReleaseStep
            setReleaseVersion,                      // : ReleaseStep
            commitReleaseVersion,                   // : ReleaseStep, performs the initial git checks
            tagRelease,                             // : ReleaseStep
            publishArtifacts,                       // : ReleaseStep, checks whether `publishTo` is properly set up
            uploadArtifacts,                        // : ReleaseStep, uploads generated artifacts to s3
            setNextVersion,                         // : ReleaseStep
            commitNextVersion,                      // : ReleaseStep
            pushChanges                             // : ReleaseStep, also checks that an upstream branch is properly configured
          )
        }
      )) settings (
	   libraryDependencies := depends
	  )

}