package com.github.arschles

import com.stackmob.newman.dsl._

object UrlBuilderExample extends App {
  val builder1 = url(http, "paypal.com") / "activatecard" / "more" / "stuff"

  //compiles with query string
  //val builder1 = (url(http, "paypal.com") / "activatecard") ? ("hello" -> "world")

  //won't compile - can't have path after query string
  //val builder1 = ((url(http, "paypal.com")) ? ("hello" -> "world")) / "activate"

  val javaURL = builder1.toURL

  println(javaURL)

  sys.exit(0)
}
