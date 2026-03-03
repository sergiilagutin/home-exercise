package sl.challenge

import cats.effect.{Resource, Sync}
import cats.implicits.toFunctorOps
import sl.challenge.model.Types.Triangle

import scala.annotation.tailrec
import scala.io.{Source, StdIn}
import scala.util.Try

trait TriangleReader[F[_]] {
  def read(): Resource[F, SourceReader[F]]
}

class SourceReader[F[_]: Sync](input: Source) {
  def getTriangle: F[Option[Triangle]] =
    Sync[F].delay(input.getLines()).map(lines => Try(lines.toArray.map(line => line.split(" ").map(_.toInt))).toOption)
}

class TriangleStringReader[F[_]: Sync](str: String) extends TriangleReader[F] {

  override def read(): Resource[F, SourceReader[F]] =
    Resource.pure(Source.fromString(str)).map(new SourceReader[F](_))
}

class TriangleFileReader[F[_]: Sync](filename: String) extends TriangleReader[F] {
  override def read(): Resource[F, SourceReader[F]] =
    Resource
      .make(Sync[F].delay(Source.fromFile(filename)))(file => Sync[F].delay(file.close()))
      .map(new SourceReader(_))
}

class TriangleStdInReader[F[_]: Sync] extends TriangleReader[F] {

  override def read(): Resource[F, SourceReader[F]] = {
    @tailrec
    def readLines(acc: List[String]): List[String] =
      Option(StdIn.readLine()) match {
        case Some(value) if value.nonEmpty => readLines(value :: acc)
        case _ => acc
      }

    Resource
      .eval(Sync[F].delay(readLines(Nil).reverse.mkString("\n")))
      .map(Source.fromString)
      .map(new SourceReader[F](_))
  }
}
