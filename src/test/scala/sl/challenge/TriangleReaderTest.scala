package sl.challenge

import org.specs2.mutable.Specification
import cats.effect.IO
import cats.effect.testing.specs2.CatsEffect

class TriangleReaderTest extends Specification with CatsEffect {
  "TriangleReader" >> {
    "read from string" >> {
      val expected = Array(
        Array(1),
        Array(2, 3),
        Array(4, 5, 6),
        Array(7, 8, 9, 10)
      )

      val string = expected
        .map(_.mkString(" "))
        .mkString("\n")

      val io = new TriangleStringReader[IO](string)
        .read()
        .use(_.getTriangle)

      io.map(_ must beSome(expected))
    }

    "read from file" >> {
      val expected = Array(
        Array(1),
        Array(2, 3),
        Array(4, 5, 6),
        Array(7, 8, 9, 10)
      )

      val io = new TriangleFileReader[IO]("./src/test/resources/data_tiny.txt")
        .read()
        .use(_.getTriangle)

      io.map(_ must beSome(expected))
    }
  }
}
