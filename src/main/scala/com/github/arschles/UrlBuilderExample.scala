package com.github.arschles

import com.stackmob.newman.dsl._

object UrlBuilderExample extends App {
  val builder1 = url(http, "paypal.com") / "activatecard"
  //val builder1 = (url(http, "paypal.com") / "activatecard") ? ("hello" -> "world")

  val javaURL = builder1.toURL

  println(javaURL)

  sys.exit(0)
}
