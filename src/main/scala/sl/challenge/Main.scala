package sl.challenge

import cats.data.OptionT
import cats.effect
import cats.effect.std.Console
import cats.effect.{ExitCode, IO, IOApp, Sync}
import cats.implicits.toShow

object Main extends IOApp {

  override def run(args: List[String]): effect.IO[ExitCode] =
    readInput[IO](args)
      .read()
      .use { sourceReader =>
        OptionT(sourceReader.getTriangle)
          .filter(TriangleValidator.isValid)
          .map(Solver.minPath)
          .map(_.show)
          .semiflatMap(str => Console[IO].println(s"Minimal path is: $str"))
          .flatTapNone(Console[IO].println("Failed to load input data."))
          .map(_ => ExitCode.Success)
          .getOrElse(ExitCode.Error)
      }

  private def readInput[F[_]: Sync](args: List[String]) =
    if (args.isEmpty)
      new TriangleStdInReader[F]
    else
      new TriangleFileReader[F](args.head)
}
