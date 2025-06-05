package com.example

import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.asServer
import org.http4k.server.Jetty
import org.http4k.core.Status.Companion.CREATED
import org.http4k.core.Status.Companion.OK

val htmlPage = """
  <html>
  <body>
  <h1 style="text-align: font-size:3em;">Hello Functional World!</h1>
  </body>
  </html>
""".trimIndent()


val app: HttpHandler = routes(
    "/greetings" bind Method.GET to ::greetings,
    "/data" bind Method.POST to ::receiveData,
)

fun greetings(req: Request): Response = Response(OK).body(htmlPage)

fun receiveData(req: Request): Response = Response(CREATED).body("Received data : ${req.bodyString()}")


fun main() {
    app.asServer(Jetty(8080)).start()
}
