import io.javalin.http.ContentType
import java.io.File

@Suppress("NAME_SHADOWING")
class Core:  Register{
    override fun registrations(): List<Registration> {
        return listOf(
            Registration(
                HttpOptions.GET,
                "/styles.css",
            ) { ctx ->
                val styling = File(STYLES_PATH).readText()
                ctx.contentType(ContentType.TEXT_CSS)
                ctx.result(styling)
            },
            Registration(
                HttpOptions.GET,
                "/assets/{asset}",
            ) { ctx ->
                val asset = ctx.pathParam("asset")
                try {
                    val asset = File("$ASSETS_PATH/$asset").readBytes()
                    ctx.contentType(ContentType.IMAGE_JPEG)
                    ctx.result(asset)
                } catch (e: Exception) {
                    ctx.status(404).result("Picture not found $asset")
                }
            }
        )
    }
}