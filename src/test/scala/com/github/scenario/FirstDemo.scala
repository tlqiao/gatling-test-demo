package com.github.scenario

import com.github.client.FirstClient
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class FirstDemo extends FirstClient {

  object getBook {
    val feeder = csv("bookId.csv").random
    val getBookCase = repeat(200) {
      feed(feeder)
        .exec(http("get book details")
          .get("api/getBook")
          .queryParam("bookId", "${bookId}"))
    }
  }

  val scn = scenario("getBook").exec(getBook.getBookCase)
  setUp(scn.inject(constantUsersPerSec(100) during (2 seconds)))
    .throttle(reachRps(100) in (5 seconds), holdFor(10 minutes))
    .protocols(httpProtocol)
}
