import org.scalatra.sbt.ScalatraPlugin
import com.mojolly.scalate.ScalatePlugin
import com.typesafe.sbt.SbtStartScript

organization := "edu.luc.etl"

name := "hello-scalatra-scala"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.10.1"

seq(webSettings :_*)

classpathTypes ~= (_ + "orbit")

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= {
  object V {
    val scalaz = "7.0.0-M9"
    val scalatra = "2.2.1"
  }
  Seq(
    "org.scalatra"            %% "scalatra"          % V.scalatra,
    "org.scalatra"            %% "scalatra-scalate"  % V.scalatra,
    "org.scalatra"            %% "scalatra-specs2"   % V.scalatra            % "test",
    "org.scalatra"            %% "scalatra-swagger"  % V.scalatra,
    "org.scalatra"            %% "scalatra-json"     % V.scalatra,
    "org.json4s"              %% "json4s-native"     % "3.2.4",
    "ch.qos.logback"          %  "logback-classic"   % "1.0.6"               % "runtime",
    "org.eclipse.jetty"       %  "jetty-webapp"      % "8.1.8.v20121106"     % "compile;container",
    "org.eclipse.jetty.orbit" %  "javax.servlet"     % "3.0.0.v201112011016" % "compile;container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
  )
}

apps in container.Configuration <<= (deployment in Compile) map (d => Seq("/api" -> d))

ivyXML := <dependencies><exclude module="slf4j-log4j12"/></dependencies>

seq(ScalatraPlugin.scalatraWithJRebel: _*)

seq(ScalatePlugin.scalateSettings: _*)

seq(SbtStartScript.startScriptForClassesSettings: _*)
