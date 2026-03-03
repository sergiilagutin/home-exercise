package sl.challenge

import sl.challenge.model.Result
import sl.challenge.model.Types.{Path, Triangle}

object Solver {
  private type Ordering = (BigInt, BigInt) => Boolean

  private def MIN: Ordering = _ < _

  private def MAX: Ordering = _ > _

  def minPath(triangle: Triangle): Result =
    getResult(triangle)(MIN)

  def maxPath(triangle: Triangle): Result =
    getResult(triangle)(MAX)

  private def getResult(triangle: Triangle)(implicit ordering: Ordering): Result = {
    val lastRow = triangle.last.map(value => Result(List(value), value))
    triangle.dropRight(1)
      .foldRight(lastRow) {
        case (currentRow, bottomRow) =>
          val mergedNeighborhoods = mergeNeighborhoods(bottomRow)
          mergedNeighborhoods.zip(currentRow).map { case (result, currentValue) =>
            Result(currentValue :: result.path, currentValue + result.sum)
          }
      }.head
  }

  private def mergeNeighborhoods(line: Array[Result])(implicit ordering: Ordering): Array[Result] =
    line.zip(line.tail).map(pair => if (ordering(pair._1.sum, pair._2.sum)) pair._1 else pair._2)
}
