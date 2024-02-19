import io.javalin.Javalin

interface Register {
    fun register(app: Javalin, context: Any)
}