import com.github.mustachejava.DefaultMustacheFactory
import com.github.mustachejava.Mustache
import io.javalin.Javalin
import io.javalin.http.ContentType
import java.io.File
import java.io.PrintWriter
import org.commonmark.parser.Parser as MarkdownParser
import org.commonmark.renderer.html.HtmlRenderer as MarkdownHtmlRenderer

class Blogger(
    mf: DefaultMustacheFactory,
    mdParser: MarkdownParser,
    mdHtmlRenderer: MarkdownHtmlRenderer,
) : Register {
    companion object {
        val TEMPLATE_PATH = "src/main/resources/templates"
        val BLOG_PLACEHOLDERS_PATH = "src/main/resources/blog/placeholders"
        val BLOG_ENTRIES_PATH = "src/main/resources/blog/entries"
        val BLOG_RENDERS_PATH = "src/main/resources/blog/renders"
    }

    // init entry navigation
    init {
        val content = File("$BLOG_PLACEHOLDERS_PATH/entries.html").readText()
        val scopes = mapOf("content" to content)

        val destination = File("$BLOG_RENDERS_PATH/entries.html")
        val printWriter = PrintWriter(destination)
        val template = File("$TEMPLATE_PATH/navMain.mustache")
        val mustache: Mustache = mf.compile(template.bufferedReader(), "blogger/entries")
        mustache.execute(printWriter, scopes)
        printWriter.close()

        println(destination.readText())
    }

    // init each entries
    init {
        val name = "creating-my-website-from-chicken-scratch"
        val file = File("${BLOG_ENTRIES_PATH}/$name.md")
        val parsed = mdParser.parse(file.readText())
        val rendered = mdHtmlRenderer.render(parsed)
        val scopes = mapOf("content" to rendered)

        val destination = File("$BLOG_RENDERS_PATH/$name.html")
        val printWriter = PrintWriter(destination)
        val template = File("${TEMPLATE_PATH}/navMain.mustache")
        val mustache: Mustache = mf.compile(template.bufferedReader(), "blogger/entries/$name")
        mustache.execute(printWriter, scopes)
        printWriter.close()

        print(destination.readText())
    }

    fun entries(): String {
        val path = "${BLOG_RENDERS_PATH}/entries.html"
        val file = File(path)
        return file.readText()
    }

    fun get(id: String): String {
        val path = "${BLOG_RENDERS_PATH}/$id.html"
        val file = File(path)
        return file.readText()
    }

    override fun register(app: Javalin, context: Any) {
        if (context is Blogger) {
            app
                .get("/blogger") { ctx ->
                    ctx.html(context.entries())
                }
                .get("/blog-entry-assets/{asset}") { ctx ->
                    val assetName = ctx.pathParam("asset")
                    try {
                        val asset = File("src/main/resources/blog/entries/blog-entry-assets/$assetName").readBytes()
                        ctx.contentType(ContentType.IMAGE_JPEG)
                        ctx.result(asset)
                    } catch (e: Exception) {
                        ctx.status(404).result("Picture not found $assetName")
                    }
                }
                .get("/blogger/styles.css") { ctx ->
                    val styling = java.io.File("src/main/resources/styles.css").readText()
                    ctx.contentType(io.javalin.http.ContentType.TEXT_CSS)
                    ctx.result(styling)
                }
                .get("/blogger/{id}") { ctx ->
                    val id = ctx.pathParam("id")
                    val html = context.get(id)
                    ctx.html(html)
                }
        }
    }
}