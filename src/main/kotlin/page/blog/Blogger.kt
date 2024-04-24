package page.blog

import Main
import core.HttpOptions
import core.Register
import core.Registration
import Main.TEMPLATE_PATH
import com.github.mustachejava.DefaultMustacheFactory
import Main.inDebugMode
import io.javalin.http.ContentType
import io.javalin.http.Context
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
        val BLOG_ENTRIES_PATH = "src/main/resources/blog/entries"
        val BLOG_RENDERS_PATH = "src/main/resources/blog/renders"
    }

    init {
        blog("creating-my-website-from-chicken-scratch", mdParser, mdHtmlRenderer, mf)
        blog("im-trapped-in-my-html-how-do-i-escape", mdParser, mdHtmlRenderer, mf)
        blog("back  enders-spellbook-my-response-to-your-request", mdParser, mdHtmlRenderer, mf)

        if (inDebugMode()) {
            blog("pilot", mdParser, mdHtmlRenderer, mf)
        }
    }

    fun blog(blog: String, mdParser: MarkdownParser, mdHtmlRenderer: MarkdownHtmlRenderer, mustacheFactory: DefaultMustacheFactory): Unit {
        val parsed = mdParser.parse(Main::class.java.getResource("blog/entries/$blog.md").readText())
        val rendered = mdHtmlRenderer.render(parsed)
        val scopes = mapOf("content" to rendered)
        val renderPath = "$BLOG_RENDERS_PATH/$blog.html"
        val templatePath = "$TEMPLATE_PATH/navMain.mustache"
        val name = "blogger/entries/$blog"
        MustacheUtil.render(scopes, renderPath, templatePath, mustacheFactory, name)
    }

    fun entries(): String {
        val path = "$BLOG_RENDERS_PATH/entries.html"
        val file = File(path)
        return file.readText()
    }

    fun get(id: String): String {
        val path = "$BLOG_RENDERS_PATH/$id.html"
        val file = File(path)
        return file.readText()
    }

    override fun registrations(): List<Registration> {
        return listOf(
            Registration(
                HttpOptions.GET,
                "/blogger"
            ) { ctx: Context -> ctx.html(entries()) },
            Registration(
                HttpOptions.GET,
                "/blog-entry-assets/{asset}",
            ) { ctx ->
                val assetName = ctx.pathParam("asset")
                try {
                    val asset = File("src/main/resources/blog/entries/blog-entry-assets/$assetName").readBytes()
                    ctx.contentType(ContentType.IMAGE_JPEG)
                    ctx.result(asset)
                } catch (e: Exception) {
                    ctx.status(404).result("Picture not found $assetName")
                }
            },
            Registration(
                HttpOptions.GET,
                "/blogger/styles.css",
            ) { ctx ->
                val styling = File("src/main/resources/styles.css").readText()
                ctx.contentType(ContentType.TEXT_CSS)
                ctx.result(styling)
            },
            Registration(
                HttpOptions.GET,
                "/blogger/{id}",
            ) { ctx ->
                val id = ctx.pathParam("id")
                val html = get(id)
                ctx.html(html)
            }
        )
    }

    override fun name(): String {
        return "page.blog.Blogger"
    }
}