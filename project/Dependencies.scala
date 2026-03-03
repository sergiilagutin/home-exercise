import sbt._

object Dependencies {
  object TestDependencies {
    val specs2 = "org.specs2" %% "specs2-core" % "4.23.0" % Test
  }

  lazy val all : Seq[ModuleID] = Seq(
    TestDependencies.specs2
  )
}
