import io.javalin.Javalin
import io.javalin.community.ssl.SslPlugin
import java.io.File

fun inDebugMode(): Boolean {
    return System.getenv("DEBUG") == "true"
}

fun main(args: Array<String>) {
    val _app = Javalin.create { javalinConfig ->
        if (!inDebugMode()) {
            val sslPlugin = SslPlugin { conf ->
                conf.pemFromPath(
                    "/etc/letsencrypt/live/twoshotslater.com/fullchain.pem",
                    "/etc/letsencrypt/live/twoshotslater.com/privkey.pem"
                )
            }
            javalinConfig.registerPlugin(sslPlugin)
        }
    }
        .get("/") { ctx ->
            val landingPage = File("src/main/resources/foo.html")
            ctx.html(landingPage.readText())
        }
        .get("/foo") { ctx -> ctx.result("bar") }
        .start(443)
}