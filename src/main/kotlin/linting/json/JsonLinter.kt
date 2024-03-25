package linting.json
import Register
import Registration
import org.tsl.firebird.Firebird

class JsonLinter : Register {
    val firebird = Firebird()
    override fun registrations(): List<Registration> {
        return listOf(
            Registration(
                HttpOptions.GET,
                "/linting/json/debug"
            ) { context ->
                val source = """{"foo":"bar"}"""
                val jsonObject = firebird.fromString(source)
                context.json(jsonObject)
            }
        )
    }

    override fun name(): String {
        return "JsonLinter"
    }
}