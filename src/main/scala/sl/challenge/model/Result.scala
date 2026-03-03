package sl.challenge.model

import cats.Show

case class Result(path: List[Int], val sum: BigInt)

object Result {
  implicit val resultShow: Show[Result] =
    Show.show(result => s"${result.path.mkString(" + ")} = ${result.sum}")
}
