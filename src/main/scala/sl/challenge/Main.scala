package sl.challenge

import cats.data.OptionT
import cats.effect
import cats.effect.std.Console
import cats.effect.{ExitCode, IO, IOApp, Sync}
import cats.implicits.toShow

object Main extends IOApp {

  override def run(args: List[String]): effect.IO[ExitCode] =
    makeResourceProvider[IO](args).getResource
      .use { triangleReader =>
        OptionT(triangleReader.getTriangle)
          .filter(TriangleValidator.isValid)
          .map(Solver.minPath)
          .map(_.show)
          .semiflatMap(str => Console[IO].println(s"Minimal path is: $str"))
          .flatTapNone(Console[IO].println("Failed to load input data."))
          .map(_ => ExitCode.Success)
          .getOrElse(ExitCode.Error)
      }

  private def makeResourceProvider[F[_]: Sync](args: List[String]) =
    if (args.isEmpty)
      new StdInResourceProvider[F]
    else
      new FileResourceProvider[F](args.head)
}
