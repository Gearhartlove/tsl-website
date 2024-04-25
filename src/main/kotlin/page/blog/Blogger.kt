package page.blog

import core.HttpOptions
import core.Register
import core.Registration
import io.javalin.http.ContentType
import io.javalin.http.Context
import page.WrapperV1
import java.io.File
import org.commonmark.parser.Parser as MarkdownParser
import org.commonmark.renderer.html.HtmlRenderer as MarkdownHtmlRenderer

class Blogger(
    val mdParser: MarkdownParser,
    val mdHtmlRenderer: MarkdownHtmlRenderer,
) : Register {

    val blogTitles = mutableListOf(
        BlogTitle("creating-my-website-from-chicken-scratch", "Creating my website from chicken scratch"),
        BlogTitle("im-trapped-in-my-html-how-do-i-escape", "I'm trapped in my HTML, how do I escape?"),
        BlogTitle("backenders-spellbook-my-response-to-your-request", "Backender's Spellbook : my response to your request")
    )

    private fun entries() = WrapperV1.wrap(Page.entries(blogTitles))

    private fun blog(blog: String): String = WrapperV1.wrap(Page.blog(blog, mdParser, mdHtmlRenderer))

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
                val html = blog(id)
                ctx.html(html)
            }
        )
    }

    override fun name(): String {
        return "page.blog.Blogger"
    }
}