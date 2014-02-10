package com.github.arschles.futurepatterns

import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps

object FirstOneWins extends App {

  val futures = (0 until 5).map { i: Int =>
    produceFuture(i)
  }.toList

  val firstCompleted = Future.firstCompletedOf(futures)

  val (num, resp) = Await.result(firstCompleted, 2 seconds)
  println(s"# $num completed first with $resp")

  sys.exit(0)
}
