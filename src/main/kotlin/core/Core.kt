package core

import Main
import io.javalin.http.ContentType
import java.io.File
import kotlin.reflect.jvm.jvmName

@Suppress("NAME_SHADOWING")
class Core : Register {
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
                "/assets/{extension}/{assetName}",
            ) { ctx ->
                val extension = ctx.pathParam("extension")
                val assetName = ctx.pathParam("assetName")

                val asset = Main::class.java.getResource("/assets/$assetName")?.readBytes()

                if (asset == null) {
                    ctx.status(404).result("Asset not found $asset")
                } else {
                    val contentTypeMatch = ContentType.entries.filter { entry ->
                        entry.extensions.contains(extension)
                    }

                    assert(contentTypeMatch.count() == 1, { "Found multiple assets. There should only be one." })

                    ctx.contentType(contentTypeMatch.first()).result(asset)
                }
            }
        )
    }

    override fun name(): String {
        return Core::class.jvmName
    }
}