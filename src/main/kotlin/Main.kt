package com.example

import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.asServer
import org.http4k.server.Jetty
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.path


fun main() {
    val app: HttpHandler = routes(
        "/todo/{user}/{list}" bind Method.GET to ::showList
    )
    val server = app.asServer(Jetty(8080)).start()

}

fun showList(req: Request): Response {
    val user: String? = req.path("user")
    val list: String? = req.path("list")

    val htmlPage = """
  <html>
  <body>
  <h1>Zettai</h1>
  <p>Here is the list <b>$list</b> of user <b>$user</b> </p>
  <h1 style="text-align: font-size:3em;">Hello Functional World!</h1>
  </body>
  </html>
""".trimIndent()

    return Response(OK).body(htmlPage)
}



