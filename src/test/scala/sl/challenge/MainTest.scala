package sl.challenge

import cats.effect.ExitCode
import cats.effect.testing.specs2.CatsEffect
import org.specs2.mutable.Specification

class MainTest extends Specification with CatsEffect {
  "Main" >> {
    "handle small data and exit with success" >> {
      Main.run(List("./src/main/resources/data_small.txt"))
        .map(_ mustEqual ExitCode.Success)
    }
  }
}
