package sl.challenge

import org.specs2.mutable.Specification

class TriangleValidatorTest extends Specification {
  "TriangleValidator" >> {
    "succeed on valid triangle" >> {
      val triangle = Array(
        Array(1),
        Array(2, 3),
        Array(4, 5, 6)
      )

      TriangleValidator.isValid(triangle) must beTrue
    }

    "fail on invalid triangle" >> {
      val triangle = Array(
        Array(1),
        Array(2, 3),
        Array(4, 5)
      )

      TriangleValidator.isValid(triangle) must beFalse
    }

    "fail on empty triangle" >> {
      TriangleValidator.isValid(Array()) must beFalse
    }

    "fail on truncated triangle" >> {
      val triangle = Array(
        Array(2, 3),
        Array(4, 5, 6),
        Array(7, 8, 9, 10)
      )

      TriangleValidator.isValid(triangle) must beFalse
    }
  }
}
