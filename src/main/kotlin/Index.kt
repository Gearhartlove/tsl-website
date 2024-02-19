import io.javalin.Javalin
import java.io.File

class Index : Register {
    override fun register(app: Javalin, context: Any) {
        app
            .get("/") { ctx ->
                val index = File(INDEX_PATH)
                ctx.html(index.readText())
            }
    }

}