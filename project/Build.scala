import sbt._
import Keys._

// sbt-release plugin
import sbtrelease.ReleasePlugin._
import sbtrelease._
import ReleaseStateTransformations._
import sbtrelease.ReleasePlugin.ReleaseKeys._


object GeodudeBuild extends Build with Crossmapping {

  import Dependencies._

  def dispatchVersion = "0.8.9"
  
  def databinder(name: String, v: String = dispatchVersion) =
       "net.databinder" %% ("dispatch-" + name) % v cross CVMapping10010
  
  def dispatchClassicLibs: Seq[sbt.ModuleID]  =
	      Seq(databinder("http"), databinder("oauth"), databinder("json"), databinder("mime"), databinder("http-json"))
  
  def depends: Seq[sbt.ModuleID] = 
  Seq(jodaTime, lift.mongoRecord, lift.mongo, lift.json, lift.record) ++ 
  dispatchClassicLibs ++ 
  rogueAll 
  
  lazy val geodude = Project(
    id = "geodude",
    base = file("."),
    settings = Defaults.defaultSettings ++ releaseSettings ++ net.virtualvoid.sbt.graph.Plugin.graphSettings ++ 
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
          //  uploadArtifacts,                        // : ReleaseStep, uploads generated artifacts to s3
            setNextVersion,                         // : ReleaseStep
            commitNextVersion,                      // : ReleaseStep
            pushChanges                             // : ReleaseStep, also checks that an upstream branch is properly configured
          )
        }
      )) settings (
	      libraryDependencies := depends
	  )

}