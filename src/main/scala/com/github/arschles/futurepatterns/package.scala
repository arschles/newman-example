package com.github.arschles

import com.stackmob.newman.response.{HttpResponseCode, HttpResponse}
import com.stackmob.newman.{RawBody, Headers}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import scala.util.Random

package object futurepatterns {
  val rand = new Random(System.currentTimeMillis())
  val httpResponse = HttpResponse(HttpResponseCode.Ok, Headers("hello" -> "world"), RawBody("the response"))

  //sleep for between 0 and 1000 milliseconds
  def randSleep(num: Int) {
    val sleepDur = rand.nextInt(1000 - num)
    Thread.sleep(sleepDur)
  }

  def produceFuture(num: Int) = Future {
    randSleep(num)
    num -> httpResponse
  }
}
