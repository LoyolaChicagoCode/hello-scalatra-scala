package edu.luc.etl.scalatra.hello

import org.scalatra.test.specs2._
import org.eclipse.jetty.servlet.ServletHolder

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class MyScalatraServletSpec extends ScalatraSpec { def is =
  "GET / on MyScalatraServlet"                     ^
    "should return status 200"                  ! root200^
                                                end

  addServlet(new MyScalatraServlet, "/*")

  def root200 = get("/") {
    status must_== 200
  }

  implicit lazy val _ = new MyScalatraSwagger
}
