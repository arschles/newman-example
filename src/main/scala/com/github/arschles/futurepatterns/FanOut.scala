package com.github.arschles.futurepatterns

import com.stackmob.newman._
import com.stackmob.newman.dsl._

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

import scala.language.postfixOps

object FanOut extends App {

  implicit val client = new ApacheHttpClient()

  val requests = List(GET(url(http, "paypal.com/business")), GET(url(http, "paypal.com/activatecard")))

  val responses = requests.map { r: HeaderBuilder =>
    r.apply.map { resp =>
      r.toRequest -> resp
    }
  }.toList

  val resp = Future.sequence(responses)

  println(Await.result(resp, 5 seconds))

  sys.exit(0)
}
