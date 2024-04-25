package core

import Main
import io.javalin.http.ContentType
import java.io.File
import kotlin.reflect.jvm.jvmName

@Suppress("NAME_SHADOWING")
class Core: Register {
    override fun registrations(): List<Registration> {
        return listOf(
            Registration(
                HttpOptions.GET,
                "/styles.css",
            ) { ctx ->
                val styling = File(Main.STYLES_PATH).readText()
                ctx.contentType(ContentType.TEXT_CSS)
                ctx.result(styling)
            },
            Registration(
                HttpOptions.GET,
                "/assets/{asset}",
            ) { ctx ->
                val asset = ctx.pathParam("asset")
                try {
                    val asset = File("${Main.ASSETS_PATH}/$asset").readBytes()
                    ctx.contentType(ContentType.IMAGE_JPEG)
                    ctx.result(asset)
                } catch (e: Exception) {
                    ctx.status(404).result("Picture not found $asset")
                }
            }
        )
    }

    override fun name(): String {
        return Core::class.jvmName
    }
}