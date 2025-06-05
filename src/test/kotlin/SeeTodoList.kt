import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import org.http4k.server.asServer
import org.http4k.server.Jetty
import org.junit.Test


data class User(val name: String);
data class ToDoList(val listName: ListName, val items: List<ToDoItem>)
data class ListName(val name: String);
data class ToDoItem(val description: String)
enum class ToDoStatus {Todo,InProgress,Done,Blocked}


class Zettai() : HttpHandler {
    val routes = routes(
        "/todo/{user}/{list}" bind Method.GET to ::showList
    )

    override fun invoke(req: Request): Response = routes(req)
    private fun showList(req: Request): Response {
        val user = req.path("user").orEmpty()
        val list = req.path("list").orEmpty()

        val htmlPage = """
  <html>
  <body>
  <h1>Zettai</h1>
  <p>Here is the list <b>$list</b> of user <b>$user</b> </p>
  </body>
  </html>
""".trimIndent()

        return Response(Status.OK).body(htmlPage)


    }


}

class SeeTodoList {

    @Test
    fun list_owners_see_their_list() {
        val user = 'jinheon'
        val listName = "shopping"
        val foodTobuy = listOf('carrots', 'appples', 'bread')
        startApp(user, listName, foodTobuy)
    }

    private fun startApp(user: String, listName: String, foodTobuy: List<String>) {
        val server = Zettai().asServer(Jetty(8081)).start()

    }
}
