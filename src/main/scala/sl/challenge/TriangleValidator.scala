package sl.challenge

import sl.challenge.model.Types.Triangle

object TriangleValidator {
  def isValid(triangle: Triangle): Boolean =
    triangle
      .zip(triangle.tail)
      .forall { case (currentRow, nextRow) =>
        currentRow.length == nextRow.length - 1
      }
}
