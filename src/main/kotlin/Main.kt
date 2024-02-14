import io.javalin.Javalin
import io.javalin.community.ssl.SslPlugin

val sslPlugin = SslPlugin { conf ->
    conf.pemFromPath(
        "/etc/letsencrypt/live/twoshotslater.com/fullchain.pem",
        "/etc/letsencrypt/live/twoshotslater.com/privkey.pem"
    )
}

fun main(args: Array<String>) {
    val app = Javalin.create { javalinConfig ->
        javalinConfig.registerPlugin(sslPlugin)
    }
        .get("/") { ctx -> ctx.result("Hello World") }
        .get("/foo") { ctx -> ctx.result("bar") }
        .start(443)
}