package sl.challenge.model

case class Result(path: List[Int]) {
  lazy val sum: Int = path.sum
}
