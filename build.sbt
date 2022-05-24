name := "encryption_udf"

version := "0.1"

scalaVersion := "2.12.10"

libraryDependencies += "com.macasaet.fernet" % "fernet-java8" % "1.4.2"
libraryDependencies += "org.apache.hive" % "hive-exec" % "2.3.9" % "provided"
libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.10.1" % "provided"
libraryDependencies += "org.pentaho" % "pentaho-aggdesigner-algorithm" % "5.1.5-jhyde" % Test


ThisBuild / resolvers += Resolver.mavenLocal
resolvers += "Cascading repo" at "https://conjars.org/repo"

assemblyMergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf")     => MergeStrategy.discard
  case m if m.toLowerCase.matches("meta-inf.*\\.sf$") => MergeStrategy.discard
  case "log4j.properties"                             => MergeStrategy.discard
  case m if m.toLowerCase.startsWith("meta-inf/services/") =>
    MergeStrategy.concat
  case _ => MergeStrategy.first
}

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
