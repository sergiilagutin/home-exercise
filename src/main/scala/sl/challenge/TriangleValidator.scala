package sl.challenge

import sl.challenge.model.Types.Triangle

object TriangleValidator {
  def isValid(triangle: Triangle): Boolean =
    triangle.nonEmpty &&
      triangle.head.length == 1 &&
      triangle
        .zip(triangle.tail)
        .forall { case (currentRow, nextRow) =>
          currentRow.length == nextRow.length - 1
        }
}
