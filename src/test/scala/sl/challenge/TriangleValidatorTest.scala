package sl.challenge

import org.specs2.mutable.Specification

class TriangleValidatorTest extends Specification {
  "TriangleValidator" >> {
    "succeed on valid triangle" >> {
      val triangle = Array(
        Array(1),
        Array(2, 3),
        Array(4, 5, 6),
      )

      TriangleValidator.isValid(triangle) must beTrue
    }

    "fail on invalid triangle" >> {
      val triangle = Array(
        Array(1),
        Array(2, 3),
        Array(4, 5),
      )

      TriangleValidator.isValid(triangle) must beFalse
    }
  }
}
