import com.github.mustachejava.MustacheFactory
import mustache.MustacheUtil
import java.io.File

class Index(mf: MustacheFactory) : Register {
    companion object {
        val INDEX_RENDER_PATH = "src/main/resources/index/renders"
        val INDEX_PLACEHOLDERS_PATH = "src/main/resources/index/placeholders"
    }

    init {
        // KGF : todo : put content on the page
        val scopes = mapOf("content" to "")
        val renderPath = "${INDEX_RENDER_PATH}/index.html"
        val templatePath = "${TEMPLATE_PATH}/navMain.mustache"
        val name = "index"

        MustacheUtil.render(scopes, renderPath, templatePath, mf, name)
    }

    override fun registrations(): List<Registration> {
        return listOf(
            Registration(
                HttpOptions.GET,
                "/",
            ) { ctx ->
                ctx.html(File("${INDEX_RENDER_PATH}/index.html").readText())
            }
        )
    }

}