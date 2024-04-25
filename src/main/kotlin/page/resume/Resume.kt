package page.resume

import core.HttpOptions
import core.Register
import core.Registration
import io.javalin.http.ContentType
import Main
import kotlin.reflect.jvm.jvmName

class Resume : Register {
    override fun registrations(): List<Registration> {
        return listOf(
            Registration(
                HttpOptions.GET,
                "/resume",
            ) { ctx ->
                ctx.contentType(ContentType.APPLICATION_PDF)
                    .result(Main::class.java.getResource("assets/Resume-Kristoff-Finley.pdf").readBytes())
            }
        )
    }

    override fun name(): String {
        return Resume::class.jvmName
    }
}