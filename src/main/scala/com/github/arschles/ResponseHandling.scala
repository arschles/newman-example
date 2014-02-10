package com.github.arschles

import com.stackmob.newman._
import com.stackmob.newman.dsl._
import com.stackmob.newman.response.HttpResponseCode

import net.liftweb.json.scalaz.JsonScalaz._
import net.liftweb.json.JsonAST.JValue

import scalaz.Success

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object ResponseHandling extends App {

  case class Response()

  implicit val responseJSONR: JSONR[Response] = new JSONR[Response] {
    override def read(json: JValue): Result[Response] = {
      Success(Response())
    }
  }

  implicit val client = new ApacheHttpClient()
  val resFail = GET(url(http, "paypal.com")).apply.expectJSONBody[Throwable, Response](HttpResponseCode.Ok)

  val resPass = GET(url(http, "paypal.com")).apply.handleCode(HttpResponseCode.Ok) { resp =>
    Success(Response())
  }

  println(Await.result(resFail, 10.seconds))
  println(Await.result(resPass, 10.seconds))

  sys.exit(0)
}
