package com.github.arschles.extendingclients

import com.stackmob.newman._
import com.stackmob.newman.dsl._
import com.stackmob.newman.request._
import com.stackmob.newman.response._

import java.net.URL

import scala.concurrent._
import scala.concurrent.duration._

/**
 * a stubbed out version of an HttpClient.
 * you might use this for testing your code, providing a control for an experiment, etc...
 */
class StubHttpClient extends HttpClient {
  private val stubHttpResponse = Future.successful {
    HttpResponse(HttpResponseCode.Ok, Headers("hello" -> "world"), RawBody("stub"))
  }

  def get(u: URL, h: Headers) = GetRequest(u, h)(stubHttpResponse)

  def post(u: URL, h: Headers, b: RawBody) = PostRequest(u, h, b)(stubHttpResponse)

  def put(u: URL, h: Headers, b: RawBody) = PutRequest(u, h, b)(stubHttpResponse)

  def delete(u: URL, h: Headers) = DeleteRequest(u, h)(stubHttpResponse)

  def head(u: URL, h: Headers) = HeadRequest(u, h)(stubHttpResponse)
}

object StubClient extends App {
  implicit val client = new StubHttpClient
  //implicit val client = new ApacheHttpClient()
  //...

  val resp = GET(url(http, "paypal.com")).addHeaders(Headers("hello" -> "world")).apply
  //...

  println(Await.result(resp, 1.second))

  sys.exit(0)
}
