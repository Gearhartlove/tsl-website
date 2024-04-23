import io.javalin.http.ContentType
import org.eclipse.jetty.util.Loader.getResource
import java.io.File

class Resume : Register {
    override fun registrations(): List<Registration> {
        return listOf(
            Registration(
                HttpOptions.GET,
                "/resume",
            ) { ctx ->
                ctx.contentType(ContentType.APPLICATION_PDF)
                    .result(Resume::class.java.getResource("assets/Resume-Kristoff-Finley.pdf").readBytes());
            }
        )
    }

    override fun name(): String {
        return "Resume"
    }
}