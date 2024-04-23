package page.index

import HttpOptions
import Register
import Registration
import com.github.mustachejava.MustacheFactory

class Index(mf: MustacheFactory) : Register {

    private val page: Page = Page()

    override fun registrations(): List<Registration> {
        return listOf(
            Registration(
                HttpOptions.GET,
                "/",
            ) { ctx ->
                ctx.html(page.html)
            }
        )
    }

    override fun name(): String {
        return "Index"
    }

}