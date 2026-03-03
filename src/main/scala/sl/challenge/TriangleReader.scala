package sl.challenge

import cats.effect.{Resource, Sync}
import cats.implicits.toFunctorOps
import sl.challenge.model.Types.Triangle

import scala.io.Source
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
