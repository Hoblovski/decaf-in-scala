package decaf.driver

import java.io.Reader

import decaf.frontend.parsing.Parser
import decaf.frontend.tac.Tac
import decaf.frontend.tacgen.TacGen
import decaf.frontend.tree.TypedTree
import decaf.frontend.typecheck.{Namer, Typer}

class Tasks(implicit val opt: Opt) {

  trait Task[T, U] {
    def run(in: T): Option[U]

    def >>[V](next: Phase[U, V]): Task[T, V] = TCons(this, next)
  }

  case class TNil[T, U](phase: Phase[T, U]) extends Task[T, U] {
    override def run(in: T): Option[U] = phase(in)
  }

  case class TCons[T, U, V](first: Task[T, U], last: Phase[U, V]) extends Task[T, V] {
    override def run(in: T): Option[V] = first.run(in).flatMap(last.apply)
  }

  val parse = TNil(new Parser)

  val typeCheck: Task[Reader, TypedTree.Tree] = parse >> new Namer >> new Typer

  val tac: Task[Reader, Tac.Program] = typeCheck >> new TacGen
}