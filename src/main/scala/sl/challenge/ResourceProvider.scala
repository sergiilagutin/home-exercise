package sl.challenge

import cats.effect.{Resource, Sync}
import cats.implicits.toFunctorOps
import sl.challenge.model.Types.Triangle

import scala.annotation.tailrec
import scala.io.{Source, StdIn}
import scala.util.Try

trait ResourceProvider[F[_]] {
  def getResource: Resource[F, TriangleReader[F]]
}

class TriangleReader[F[_]: Sync](input: Source) {
  def getTriangle: F[Option[Triangle]] =
    Sync[F].delay(input.getLines()).map(lines => Try(lines.toArray.map(line => line.split(" ").map(_.toInt))).toOption)
}

class StringResourceProvider[F[_]: Sync](str: String) extends ResourceProvider[F] {

  override def getResource: Resource[F, TriangleReader[F]] =
    Resource.pure(Source.fromString(str)).map(new TriangleReader[F](_))
}

class FileResourceProvider[F[_]: Sync](filename: String) extends ResourceProvider[F] {
  override def getResource: Resource[F, TriangleReader[F]] =
    Resource
      .make(Sync[F].delay(Source.fromFile(filename)))(file => Sync[F].delay(file.close()))
      .map(new TriangleReader(_))
}

class StdInResourceProvider[F[_]: Sync] extends ResourceProvider[F] {

  override def getResource: Resource[F, TriangleReader[F]] = {
    @tailrec
    def readLines(acc: List[String]): List[String] =
      Option(StdIn.readLine()) match {
        case Some(value) if value.nonEmpty => readLines(value :: acc)
        case _ => acc
      }

    Resource
      .eval(Sync[F].delay(readLines(Nil).reverse.mkString("\n")))
      .map(Source.fromString)
      .map(new TriangleReader[F](_))
  }
}
