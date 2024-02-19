import io.javalin.Javalin
import io.javalin.config.JavalinConfig

interface Register {
    fun register(app: Javalin, context: Any)
}