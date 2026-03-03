import sbt.*

object Dependencies {

  object Cats {
    private val effect = "org.typelevel" %% "cats-effect" % "3.6.3"
    private val core = "org.typelevel" %% "cats-core" % "2.13.0"

    val all: Seq[ModuleID] = Seq(effect, core)
  }

  object TestDependencies {
    private val specs2 = "org.specs2" %% "specs2-core" % "4.23.0" % Test
    private val cats = "org.typelevel" %% "cats-effect-testing-specs2" % "1.7.0" % Test

    val all: Seq[ModuleID] = Seq(specs2, cats)
  }

  lazy val all: Seq[ModuleID] = {
    Cats.all ++
      TestDependencies.all
  }
}
