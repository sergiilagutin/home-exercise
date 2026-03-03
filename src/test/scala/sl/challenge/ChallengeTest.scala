package sl.challenge

import cats.effect.IO
import cats.effect.testing.specs2.CatsEffect
import org.specs2.mutable.Specification
import sl.challenge.model.Result

case class TestParameters(
                           label: String,
                           lineCount: Int,
                           filename: String
                         )

trait ChallengeTest extends Specification with CatsEffect {
  val testParameters: TestParameters
  Seq(
    ("Small input", 50, "./src/main/resources/data_small.txt"),
    ("Big input", 2000, "./src/main/resources/data_big.txt"),
  )

  "Solution" >> {
    s"calculate min path for ${testParameters.label}" >> {
      val minPath = Result(List.fill(testParameters.lineCount)(1), testParameters.lineCount)
      new FileResourceProvider[IO](testParameters.filename)
        .getResource
        .use(_.getTriangle)
        .map(_.map(triangle => Solver.minPath(triangle)))
        .map(_ must beSome(minPath))
    }

    s"calculate max path for ${testParameters.label}" >> {
      val maxPath = Result((1 to testParameters.lineCount).toList, (1 to testParameters.lineCount).sum)
      new FileResourceProvider[IO](testParameters.filename)
        .getResource
        .use(_.getTriangle)
        .map(_.map(triangle => Solver.maxPath(triangle)))
        .map(_ must beSome(maxPath))
    }
  }
}

class SmallDataTest extends ChallengeTest {

  override val testParameters: TestParameters =
    TestParameters("Small input", 50, "./src/main/resources/data_small.txt")
}

class BigDataTest extends ChallengeTest {

  override val testParameters: TestParameters =
    TestParameters("Big input", 2000, "./src/main/resources/data_big.txt")
}