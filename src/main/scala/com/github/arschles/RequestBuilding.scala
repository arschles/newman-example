package com.github.arschles

import com.stackmob.newman._
import com.stackmob.newman.dsl._

import scala.concurrent._
import scala.concurrent.duration._

object RequestBuilding extends App {
  //
  //implicit val client = new FinagleHttpClient()
  implicit val client = new SprayHttpClient()

  val resp1 = GET(url(http, "paypal.com")).addHeaders("hello" -> "world").apply
  //won't compile - can't add body to GET request
  //val resp1 = GET(url(http, "paypal.com")).addBody("hello world").apply


  val resp2 = POST(url(http, "paypal.com")).
    addHeaders("hello" -> "world").
    addBody("hello world").
    apply

  val resp3 = DELETE(url(http, "paypal.com")).apply
  //...

  println(Await.result(resp1, 5.seconds))
  println(Await.result(resp2, 5.seconds))

  sys.exit(0)
}
