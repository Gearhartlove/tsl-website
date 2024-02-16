import io.javalin.Javalin
import io.javalin.community.ssl.SslPlugin
import io.javalin.http.ContentType
import java.io.File
import org.commonmark.parser.Parser as MarkdownParser
import org.commonmark.renderer.html.HtmlRenderer as MarkdownHtmlRenderer

fun inDebugMode(): Boolean {
    return System.getenv("DEBUG") == "true"
}

fun main() {
    val mdParser = MarkdownParser.builder().build()
    val mdHtmlRenderer = MarkdownHtmlRenderer.builder().build()
    val blogger = Blogger()
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
            val index = File("src/main/resources/index/index.html")
            ctx.html(index.readText())
        }
        .get("/blog") { ctx ->
            val blog = File("src/main/resources/blog/blog.html")
            ctx.html(blog.readText())
        }
        .get("/blog-entry-assets/{asset}") { ctx ->
            val assetName = ctx.pathParam("asset")
            try {
                val asset = File("src/main/resources/blog/entries/blog-entry-assets/$assetName").readBytes()
                ctx.contentType(ContentType.IMAGE_JPEG)
                ctx.result(asset)
            } catch(e: Exception) {
                ctx.status(404).result("Picture not found $assetName")
            }

        }
        .get("/blogger/{id}") { ctx ->
            val id = ctx.pathParam("id")
            val raw = blogger.get(id)
            val parsed = mdParser.parse(raw)
            val rendered = mdHtmlRenderer.render(parsed)
            ctx.html(rendered)
        }
        .get("/styles.css") { ctx ->
            val styling = File("src/main/resources/styles.css").readText()
            ctx.contentType(ContentType.TEXT_CSS)
            ctx.result(styling)
        }
        .start(443)
}