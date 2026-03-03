package sl.challenge

import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import sl.challenge.model.Result

import scala.util.Random

class SolverTest extends Specification {
  trait SmallTriangle extends Scope {
    val triangle = Array(
      Array(1),
      Array(2, 3),
      Array(4, 5, 6),
      Array(7, 8, 9, 10)
    )

    val minPath = Result(List(1, 2, 4, 7), 14)

    val maxPath = Result(List(1, 3, 6, 10), 20)
  }

  "Solver" >> {
    "find min path" in new SmallTriangle {
      Solver.minPath(triangle) must beEqualTo(minPath)
    }

    "find max path" in new SmallTriangle {
      Solver.maxPath(triangle) must beEqualTo(maxPath)
    }
  }
}
