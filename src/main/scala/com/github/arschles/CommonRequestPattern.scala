package com.github.arschles

import com.stackmob.newman._
import dsl._

import scala.concurrent._
import scala.concurrent.duration._

object CommonRequestPattern extends App {
  implicit val client = new ApacheHttpClient()
  //or swap out
  //implicit val client = new FinagleHttpClient()
  //implicit val client = new SprayHttpClient()

  val u = url(http, "paypal.com")
  val headers = Headers("Hello" -> "World")

  val req = client.get(u, headers)

  val respFuture = req.apply

  println(s"executing $req")
  println(Await.result(respFuture, 5.seconds))

  sys.exit(0)
}
