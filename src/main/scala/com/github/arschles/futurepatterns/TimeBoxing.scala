package com.github.arschles.futurepatterns

import com.stackmob.newman._
import com.stackmob.newman.dsl._

import scala.concurrent._
import scala.concurrent.duration._

import scala.language.postfixOps

object TimeBoxing extends App {

  implicit val client = new ApacheHttpClient()

  val url1 = url(http, "paypal.com/business")
  val url2 = url(http, "paypal.com/buy")
  val businessResp = GET(url1).apply
  val buyResp = GET(url2).apply

  //we want people to buy stuff, so give it some grace period
  println(Await.result(buyResp, 2 seconds))

  //we can fall back if we don't get the business page back in time
  println(Await.result(businessResp, 500 milliseconds))

  sys.exit(0)
}
