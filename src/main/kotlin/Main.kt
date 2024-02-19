import com.github.mustachejava.DefaultMustacheFactory
import io.javalin.Javalin
import io.javalin.community.ssl.SslPlugin
import io.javalin.http.ContentType
import java.io.File
import org.commonmark.parser.Parser as MarkdownParser
import org.commonmark.renderer.html.HtmlRenderer as MarkdownHtmlRenderer

fun inDebugMode(): Boolean {
    return System.getenv("DEBUG") == "true"
}

const val STYLES_PATH = "src/main/resources/styles.css"
const val ASSETS_PATH = "src/main/resources/assets"
const val TEMPLATE_PATH = "src/main/resources/templates"


fun main() {
    val mdParser = MarkdownParser.builder().build()
    val mdHtmlRenderer = MarkdownHtmlRenderer.builder().build()
    val mf = DefaultMustacheFactory()

    val blogger = Blogger(mf, mdParser, mdHtmlRenderer)
    val index = Index(mf)

    val app = Javalin.create { javalinConfig ->
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

    blogger.register(app, blogger)
    index.register(app, index)

    app
        .get("/styles.css") { ctx ->
            val styling = File(STYLES_PATH).readText()
            ctx.contentType(ContentType.TEXT_CSS)
            ctx.result(styling)
        }
        .get("/assets/{asset}") { ctx ->
            val asset = ctx.pathParam("asset")
            try {
                val asset = File("$ASSETS_PATH/$asset").readBytes()
                ctx.contentType(ContentType.IMAGE_JPEG)
                ctx.result(asset)
            } catch (e: Exception) {
                ctx.status(404).result("Picture not found $asset")
            }
        }
        .start(443)
}