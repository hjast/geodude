import sbt._

object Resolvers {

  val javaNet = JavaNet1Repository
  val mavenLocal = "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository"
  val sunrepo = "Sun Maven2 Repo" at "http://download.java.net/maven/2"
  val databinder = "DataBinder" at "http://databinder.net/repo"

  //For scala releases
  val typeSafeRelease = "TypeSafe Release" at "http://repo.typesafe.com/typesafe/releases/"
  val typeSafeSnapshots = "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"

  val scalatoolsSnapshots = "Scalatools Snapshots" at "http://scala-tools.org/repo-snapshots"
  val sonatypeSnapshots = "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots"
  val akkaSnapshots = "Akka Snapshots" at "http://repo.akka.io/snapshots/"

  val sonatypeReleases = "releases" at "http://oss.sonatype.org/content/repositories/releases"

  val sprayResolver = "spray repo" at "http://repo.spray.io"
  val all =
    Seq(mavenLocal, typeSafeRelease, typeSafeSnapshots, javaNet, sunrepo,
      scalatoolsSnapshots, sonatypeSnapshots, sonatypeReleases, akkaSnapshots, sprayResolver)
  }


trait Scalaz extends Crossmapping {
	val scalazVersion = "7.0.0" 
	val scalazContrib = "0.1.4"
	
	/* A robust fully functional functional programming library for Scala */ 
	def scalazCore = "org.scalaz" %% "scalaz-core" % "7.0.0" withSources() cross CVMapping10010
	
  def scalazContribCore  = "org.typelevel" %% "scalaz-contrib-210"        % scalazContrib
  def scalazValidation = "org.typelevel" %% "scalaz-contrib-validation" % scalazContrib
  def scalazUndo =  "org.typelevel" %% "scalaz-contrib-undo"       % scalazContrib
  def scalazDispatch = "org.typelevel" %% "scalaz-dispatch"           % scalazContrib
  def scalazLift =  "org.typelevel" %% "scalaz-lift"               % scalazContrib
  def scalazTime = "org.typelevel" %% "scalaz-nscala-time"        % scalazContrib
  def scalazSpire = "org.typelevel" %% "scalaz-spire"              % scalazContrib
	
}

/** Functional language extensions **/
trait Shapeless extends Crossmapping {
	
	val shapelessVersion = "1.2.4" 
	val shapelessContribVersion =  "0.1.1"
	/** 
	A library with support for advancded type level programming including a unbelievable 
	HList version. 
	*/
	def shapelessCore =  "com.chuusai" %% "shapeless" % "1.2.4" withSources()

	/** Contribution for interloping shapeless with other libraries **/
	def shapelessScalacheck =     "org.typelevel" %% "shapeless-scalacheck" % shapelessContribVersion
	def shapelessSpire =     "org.typelevel" %% "shapeless-spire" % shapelessContribVersion
	def shapelessScalaz =     "org.typelevel" %% "shapeless-scalaz" % shapelessContribVersion 

}

trait Testing extends Crossmapping {
	val specs2Version = "1.14.1-SNAPSHOT" 
	
	/** The best BDD library for Scala with a large community **/
	def specs2 = "org.specs2" %% "specs2" % specs2Version % "test" cross CVMapping10010
	
	/** Tradational JUnit **/
	def junit = "junit" % "junit" % "4.5"
}

trait WebContainers extends Crossmapping {
	val jettyVersion = "6.1.22"

	def jetty6 = "org.mortbay.jetty" % "jetty" % jettyVersion % "container"
	def jetty6Test = "org.mortbay.jetty" % "jetty" % jettyVersion % "test"
	def jetty7 = "org.eclipse.jetty" % "jetty-webapp" % "7.3.0.v20110203" % "jetty"
	def jetty7security = "org.eclipse.jetty" % "jetty-security" % "7.3.0.v20110203"  
}

trait Logging extends Crossmapping {

	def scalaLogging = "com.typesafe" %% "scalalogging-log4j" % "1.1.0-SNAPSHOT"
  	def slf4j = "org.slf4j" % "slf4j-api" % "1.7.2"
  	def logback  = "ch.qos.logback" % "logback-classic" % "0.9.30"
  	def logLady  = "org.eintr.loglady" %% "loglady" % "1.0.0"
}

trait Lift extends Crossmapping {
	val liftVersion = "2.5-RC5" //TODO Change this
	
    def lift(name: String, v: String = liftVersion) =
      "net.liftweb" %% ("lift-%s" format name) % v exclude("joda-time", "joda-time") cross CVMapping10010

	def liftWebkit = lift("webkit") 
	def liftMapper = lift("mapper") 
	def liftRecord = lift("record") 
	def liftWizard = lift("wizard") 
	def liftJson = lift("json") 
	def  liftMongo = lift("mongodb") 
	def liftMongodbRecord = lift("mongodb-record") 
}


trait Akka extends Crossmapping {
    val akkaVersion = "2.2-M2"
	
    def akka(name: String, v: String = akkaVersion) =
      "com.typesafe.akka" %% ("akka-%s" format name) % v withSources() cross CVMapping10010
   
	  def akkaActor = akka("actor")
	  def akkaAgent = akka("agent")
	  def akkaCamel = akka("camel")
	  def akkaDataflow = akka("dataflow")
	  def akkaFileMailbox = akka("file-mailbox")
	  def akkaKernel = akka("kernel")
	  def akkaMailboxesCommon = akka("mailboxes-common")
	  def akkaOsgi = akka("osgi")
	  def akkaOsgiAries = akka("osgi-aries")
	  def akkaRemote = akka("remote")
	  def akkaSlf4j = akka("slf4j")
	  def akkaTestkit = akka("testkit")
	  def akkaTransactor = akka("transactor")
	  def akkaZeromq = akka("zeromq")
	
}

trait Java {
	val commonLang = "commons-lang" % "commons-lang" % "2.5" withSources()
    val servlet = "javax.servlet" % "servlet-api" % "2.5"
	val jodaTime = "joda-time" % "joda-time" % "2.2"
}

trait NoSql extends Crossmapping {

	val rogueVersion = "2.0.0-beta22"
	
	def rogue(name: String, v: String = rogueVersion) =
      "com.foursquare" %% ("rogue-%s" format name) % v intransitive() cross CVMapping10010
	  
	  def rogueField = rogue("field")
	  def rogueCore = rogue("core")
	  def rogueLift = rogue("lift")
	  
	  def rogueAll = Seq(rogueField, rogueCore, rogueLift)

}

trait Numerical extends Crossmapping   {
	val saddle =  "org.scala-saddle" %% "saddle" % "1.1.+"
	val spire = "org.spire-math" %% "spire" % "0.3.0" 
}

trait Spray extends Crossmapping {
	val sprayVersion = "1.1-M7"
	def spray(s: String) = "io.spray" % ("spray-%s" format s) % sprayVersion
	
	val sprayCaching = spray("caching")
	val sprayCan = spray("can")
	val sprayClient = spray("client")
	val sprayHttp = spray("http")
	val sprayHttpx = spray("httpx")
	val sprayIo = spray("io")
	val sprayRouting = spray("routing")
	val sprayServlet = spray("servlet")
	val sprayTestkit = spray("testkit")
	val sprayUtil = spray("util")

}

trait Utils extends Crossmapping{
	val scalaTime = "com.github.nscala-time" %% "nscala-time" % "0.2.0"
}



trait Reboot extends Crossmapping {
    /** Dispatch & Friends **/
  
    val rebootVersion = "0.10.0"
 
    def reboot(name: String, v: String = rebootVersion) =
      "net.databinder.dispatch" %% ("dispatch-" + name) % v cross CVMapping10010

	  def dispatchCore = reboot("core")
	  def dispatchNative = reboot("json4s-native")
	  def dispatchJackson = reboot("json4s-jackson")
	  
	  def dispatchAll = reboot("all")
	
}

trait Crossmapping {
	
    def crossMapped(mappings: (String, String)*): CrossVersion =
      CrossVersion.binaryMapped(Map(mappings: _*) orElse {
        case v => v
      })

    lazy val CVMapping10010 = crossMapped("2.10.0" -> "2.10")

}

object Dependencies
extends Scalaz
with Shapeless
with Lift
with Logging
with Spray
with Akka
with Testing
with Reboot
with Utils
with Numerical
with Java
with NoSql {

  /** Functional programming for scala **/
  object scalaz extends Scalaz {
    def core = scalazCore
    def all = Seq(scalazCore, scalazContribCore, scalazValidation, scalazUndo, scalazDispatch, scalazLift, scalazTime, scalazSpire)
  }
  
  /** Advancded typelevel programming for scala **/
  object shapeless extends Shapeless {
    def core = shapelessCore
    def scalacheck = shapelessScalacheck
    def spire = shapelessSpire
    def scalaz = shapelessScalaz
  }
  
  /** Lift libraries **/
  object lift extends Lift{
    def all = Seq(liftWebkit, liftMapper, liftRecord, liftWizard, liftJson, liftMongo, liftMongodbRecord)
    def webkit = liftWebkit
    def mapper = liftMapper
    def record = liftRecord
    def wizard = liftWizard
    def json = liftJson
    def mongo = liftMongo
    def mongoRecord = liftMongodbRecord
  }
  
  /** Scala logging libraries **/
  object logging extends Logging 

  /** Spray libraries **/
  object spray extends Spray {
    def all = Seq(sprayCaching, sprayCan, sprayClient, sprayHttpx, sprayIo, sprayRouting, sprayServlet, sprayTestkit, sprayUtil)
    def caching = sprayCaching
	def can = sprayCan
	def client = sprayClient
	def http = sprayHttp
	def httpx = sprayHttpx
	def io = sprayIo
	def routing = sprayRouting
	def servlet = sprayServlet
	def testkit = sprayTestkit
	def util = sprayUtil
  }
  
  /** Akka libraries **/
  object akka extends Akka {
	  def all = Seq(akkaActor, akkaAgent, akkaCamel, akkaDataflow, akkaFileMailbox, akkaKernel, 
		  akkaMailboxesCommon, akkaOsgi, akkaOsgiAries,akkaRemote, akkaSlf4j, akkaTransactor, akkaTestkit, akkaZeromq)
		  def actor = akkaActor
		  def agent = akkaAgent
		  def camel = akkaCamel
		  def dataflow = akkaDataflow
		  def fileMailbox = akkaFileMailbox
		  def kernel = akkaKernel
		  def mailboxesCommon = akkaMailboxesCommon
		  def osgi = akkaOsgi
		  def osgiAries = akkaOsgiAries
		  def remote = akkaRemote
		  def slf4j = akkaSlf4j
		  def transactor = akkaTransactor
		  def testkit = akkaTestkit 
		  def zeromq = akkaZeromq 
  }

  object testing extends Testing

  object reboot extends Reboot 
 
}
