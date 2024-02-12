import io.javalin.Javalin

fun main(args: Array<String>) {
    val app = Javalin.create(/*config*/)
        .get("/") { ctx -> ctx.result("Hello World") }
        .get("/foo") { ctx -> ctx.result("bar") }
        .start(7070)
}