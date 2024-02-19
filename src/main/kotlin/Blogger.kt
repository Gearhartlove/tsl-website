import com.github.mustachejava.DefaultMustacheFactory
import com.github.mustachejava.Mustache
import java.io.File
import java.io.PrintWriter
import org.commonmark.parser.Parser as MarkdownParser
import org.commonmark.renderer.html.HtmlRenderer as MarkdownHtmlRenderer

class Blogger(
    mf: DefaultMustacheFactory,
    mdParser: MarkdownParser,
    mdHtmlRenderer: MarkdownHtmlRenderer,
) {
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
        val file = File("${BLOG_ENTRIES_PATH}/creating-my-website-from-chicken-scratch.md")
        val parsed = mdParser.parse(file.readText())
        val rendered = mdHtmlRenderer.render(parsed)
        val scopes = mapOf("content" to rendered)

        val printWriter = PrintWriter(File("${BLOG_RENDERS_PATH}/creating-my-website-from-chicken-scratch.html"))
        val mustache: Mustache = mf.compile("${TEMPLATE_PATH}/navMain.mustache")
        mustache.execute(printWriter, scopes)
        printWriter.close()
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
}