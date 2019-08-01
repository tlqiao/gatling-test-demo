package com.github.client

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class FirstClient extends Simulation{
 val header = Map("Content-Type" -> "application/json")
  val httpProtocol = http.baseUrl("http://localhost:9090")
    .basicAuth("user","password")
    .headers(header)
}
