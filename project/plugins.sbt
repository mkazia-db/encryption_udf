logLevel := Level.Warn

resolvers += "bintray-sbt-assembly" at "https://dl.bintray.com/eed3si9n/sbt-plugins"

//required to build a FAT / UBER JAR
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.15.0")