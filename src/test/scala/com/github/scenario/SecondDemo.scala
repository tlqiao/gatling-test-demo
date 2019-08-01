package com.github.scenario

import com.github.client.FirstClient
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SecondDemo extends FirstClient {

  object getToken {
    val feeder = csv("user.csv").random
    val login = feed(feeder)
      .exec(http("login in app")
        .post("api/login")
        .body(StringBody(s"""{"username":"${username}","password":"abc123"}"""))
        .check(header("Authorization").saveAs("auth")
          .check(status.in(200).saveAs("tokenStatus"))
          .check(jsonPath("$.access_token").transform(it => "bear" + it)).saveAs("token")))
  }

  object viewList {
    val view = exec(http("view list")
      .get("api/list")
      .header("Authorization", "${auth}"))
  }
}