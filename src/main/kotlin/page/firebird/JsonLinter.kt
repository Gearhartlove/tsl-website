package page.firebird
import core.HttpOptions
import core.Register
import core.Registration
import org.tsl.firebird.Firebird
import kotlin.reflect.jvm.jvmName

class JsonLinter : Register {
    val firebird = Firebird()
    override fun registrations(): List<Registration> {
        return listOf(
            Registration(
                HttpOptions.GET,
                "/linting/json/{source}"
            ) { context ->
                val source = context.pathParam("source")
                val jsonObject = firebird.fromString(source)
                context.json(jsonObject)
            }
        )
    }

    override fun name(): String {
        return JsonLinter::class.jvmName
    }
}