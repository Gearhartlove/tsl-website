package page.index

import core.HttpOptions
import core.Register
import core.Registration
import kotlin.reflect.jvm.jvmName

class Index : Register {

    override fun registrations(): List<Registration> {
        return listOf(
            Registration(
                HttpOptions.GET,
                "/",
            ) { ctx ->
                ctx.html(Page.html)
            }
        )
    }

    override fun name(): String {
        return Index::class.jvmName
    }

}