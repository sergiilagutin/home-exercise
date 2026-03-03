package sl.challenge

import sl.challenge.model.Result
import sl.challenge.model.Types.{Path, Triangle}

object Solver {
  def minPath(triangle: Triangle): Result =
    getResult(triangle, _.minBy(_.sum))

  def maxPath(triangle: Triangle): Result =
    getResult(triangle, _.maxBy(_.sum))

  private def getResult(triangle: Triangle, f: => List[Result] => Result): Result =
    f(collectPaths(0, triangle).map(Result.apply))

  private def collectPaths(indexInRow: Int, triangle: Triangle): List[Path] = {
    triangle.length match {
      case 0 => List(Nil)
      case 1 => List(List(triangle.head(indexInRow)))
      case _ =>
        val head = triangle.head(indexInRow)
        collectPaths(indexInRow, triangle.tail).map(head :: _) ++
          collectPaths(indexInRow + 1, triangle.tail).map(head :: _)
    }
  }
}
