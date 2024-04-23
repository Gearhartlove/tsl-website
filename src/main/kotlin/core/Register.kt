package core

import io.javalin.Javalin
import io.javalin.http.Context

enum class HttpOptions {
    GET,
    HEAD,
    POST,
    PUT,
    DELETE,
    CONNECT,
    OPTIONS,
    TRACE,
    PATCH,
}

data class Registration(
    val httpOption: HttpOptions,
    val path: String,
    val handler: (ctx: Context) -> Unit
)

interface Register {
    fun registrations(): List<Registration>
    fun name(): String
}

fun Javalin.register(theRegister: Register): Javalin {
    println("Registering register [${theRegister.name()}]")
    theRegister.registrations().forEach { registration ->
        println("- $registration.path")
        register(registration)
    }

    return this
}

private fun Javalin.register(r: Registration): Javalin {
    when (r.httpOption) {
        HttpOptions.GET -> get(r.path, r.handler)
        HttpOptions.POST -> post(r.path, r.handler)
        HttpOptions.HEAD -> TODO()
        HttpOptions.PUT -> TODO()
        HttpOptions.DELETE -> TODO()
        HttpOptions.CONNECT -> TODO()
        HttpOptions.OPTIONS -> TODO()
        HttpOptions.TRACE -> TODO()
        HttpOptions.PATCH -> TODO()
    }

    return this
}
