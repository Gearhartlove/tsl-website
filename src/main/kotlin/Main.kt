import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import core.Core
import core.register
import page.index.Index
import io.javalin.Javalin
import io.javalin.community.ssl.SslPlugin
import io.javalin.http.staticfiles.Location
import page.firebird.JsonLinter
import page.blog.Blogger
import page.frisco.FriscoClient
import page.phonebook.Phonebook
import page.resume.Resume
import page.square.Square
import org.commonmark.parser.Parser as MarkdownParser
import org.commonmark.renderer.html.HtmlRenderer as MarkdownHtmlRenderer

@Suppress("unused")
object Main {

    const val STYLES_PATH = "src/main/resources/styles.css"
    const val ASSETS_PATH = "src/main/resources/assets"

    private fun inDebugMode(): Boolean {
        return System.getenv("DEBUG") == "true"
    }

    private fun getPort(): Int {
        return System.getenv("PORT").toInt()
    }

    fun run() {
        // misc resources
        val mdParser = MarkdownParser.builder().build()
        val mdHtmlRenderer = MarkdownHtmlRenderer.builder().build()
        val mapper = jacksonObjectMapper {}

        // clients
        val cueClient = CueClient()

        // pages
        val core = Core()
        val blogger = Blogger(mdParser, mdHtmlRenderer)
        val index = Index()
        val phonebook = Phonebook()
        val jsonLinter = JsonLinter()
        val resume = Resume()
        val square = Square()
        val friscoClient = FriscoClient(cueClient)

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
            .register(square)
            .register(friscoClient)
            .start(getPort())
    }
}

fun main() {
    Main.run()
}
