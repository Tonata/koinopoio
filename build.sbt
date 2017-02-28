name := "koinopoio"

version := "1.0"

lazy val `koinopoio` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( javaJdbc ,  cache , javaWs )

libraryDependencies += "com.typesafe.play" % "play-logback_2.11" % "2.5.1"

libraryDependencies += "org.mockito" % "mockito-all" % "2.0.2-beta"

libraryDependencies += "com.typesafe.play" % "play-ws_2.11" % "2.5.1"

libraryDependencies += "org.springframework.security" % "spring-security-core" % "4.0.3.RELEASE"

libraryDependencies += "org.apache.tika" % "tika-core" % "1.11"

libraryDependencies += "org.mongodb" % "casbah-gridfs_2.11" % "3.1.0"

libraryDependencies += "me.lessis" % "base64_2.11" % "0.2.0"

libraryDependencies += "org.apache.tika" % "tika" % "1.11"

libraryDependencies += "com.sksamuel.scrimage" % "scrimage-core_2.11" % "2.1.1"

libraryDependencies += "org.jongo" % "jongo" % "1.3.0"

libraryDependencies += "org.testng" % "testng" % "6.10"

resolvers ++= Seq(
  "Typesafe repository snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
  "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
  "softprops-maven" at "http://dl.bintray.com/content/softprops/maven",
  "Brando Repository" at "http://chrisdinn.github.io/releases/",
  "Sonatype repo" at "https://oss.sonatype.org/content/groups/scala-tools/",
  "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases",
  "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype staging" at "http://oss.sonatype.org/content/repositories/staging",
  "Java.net Maven2 Repository" at "http://download.java.net/maven/2/",
  "Twitter Repository" at "http://maven.twttr.com",
  "Websudos releases" at "https://dl.bintray.com/websudos/oss-releases/",
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  