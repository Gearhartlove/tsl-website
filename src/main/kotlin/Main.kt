import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.mustachejava.DefaultMustacheFactory
import page.index.Index
import io.javalin.Javalin
import io.javalin.community.ssl.SslPlugin
import io.javalin.http.staticfiles.Location
import linting.json.JsonLinter
import phonebook.Phonebook
import org.commonmark.parser.Parser as MarkdownParser
import org.commonmark.renderer.html.HtmlRenderer as MarkdownHtmlRenderer

fun inDebugMode(): Boolean {
    return System.getenv("DEBUG") == "true"
}

fun getPort(): Int {
    return System.getenv("PORT").toInt()
}

const val STYLES_PATH = "src/main/resources/styles.css"
const val ASSETS_PATH = "src/main/resources/assets"
const val TEMPLATE_PATH = "src/main/resources/templates"

fun main() {
    val mdParser = MarkdownParser.builder().build()
    val mdHtmlRenderer = MarkdownHtmlRenderer.builder().build()
    val mapper = jacksonObjectMapper {}
    val mf = DefaultMustacheFactory()

    val core = Core()
    val blogger = Blogger(mf, mdParser, mdHtmlRenderer)
    val index = Index(mf)
    val phonebook = Phonebook()
    val jsonLinter = JsonLinter()
    val resume = Resume()

    val app = Javalin.create { javalinConfig ->
        if (!inDebugMode()) {
            val sslPlugin = SslPlugin { conf ->
                conf.pemFromPath(
                    "/etc/letsencrypt/live/twoshotslater.com/fullchain.pem",
                    "/etc/letsencrypt/live/twoshotslater.com/privkey.pem"
                )
            }
            javalinConfig.registerPlugin(sslPlugin)

            javalinConfig.staticFiles.add("/main/assets", Location.CLASSPATH)
        }
    }
        .register(core)
        .register(index)
        .register(blogger)
        .register(phonebook)
        .register(jsonLinter)
        .register(resume)
        .start(getPort())
}