import com.github.mustachejava.DefaultMustacheFactory
import io.javalin.Javalin
import io.javalin.http.ContentType
import mustache.MustacheUtil
import java.io.File
import org.commonmark.parser.Parser as MarkdownParser
import org.commonmark.renderer.html.HtmlRenderer as MarkdownHtmlRenderer

class Blogger(
    mf: DefaultMustacheFactory,
    mdParser: MarkdownParser,
    mdHtmlRenderer: MarkdownHtmlRenderer,
) : Register {
    companion object {
        val BLOG_PLACEHOLDERS_PATH = "src/main/resources/blog/placeholders"
        val BLOG_ENTRIES_PATH = "src/main/resources/blog/entries"
        val BLOG_RENDERS_PATH = "src/main/resources/blog/renders"
    }

    // init entry navigation
    init {
        val placeholder = File("$BLOG_PLACEHOLDERS_PATH/entries.html").readText()
        val scopes = mapOf("content" to placeholder)
        val renderPath = "$BLOG_RENDERS_PATH/entries.html"
        val templatePath = "$TEMPLATE_PATH/navMain.mustache"
        val name = "blogger/entries"
        MustacheUtil.render(scopes, renderPath, templatePath, mf, name)
    }

    // init each entries
    init {
        val blog = "creating-my-website-from-chicken-scratch"
        val file = File("${BLOG_ENTRIES_PATH}/$blog.md")
        val parsed = mdParser.parse(file.readText())
        val rendered = mdHtmlRenderer.render(parsed)
        val scopes = mapOf("content" to rendered)
        val renderPath = "$BLOG_RENDERS_PATH/$blog.html"
        val templatePath = "${TEMPLATE_PATH}/navMain.mustache"
        val name = "blogger/entries/$blog"
        MustacheUtil.render(scopes, renderPath, templatePath, mf, name)
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