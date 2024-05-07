package page.frisco

import CueClient
import core.HttpOptions
import core.Register
import core.Registration
import kotlinx.coroutines.runBlocking
import page.WrapperV1
import kotlin.reflect.jvm.jvmName

class FriscoClient(val cueClient: CueClient): Register {

    override fun registrations(): List<Registration> {
        return listOf(
            Registration(
                HttpOptions.GET,
                "/frisco"
            ) { ctx ->
                ctx.html(Page.getCardDesigner())
            },
            Registration(
                HttpOptions.GET,
                "/frisco2"
            ) { ctx ->
                ctx.html(WrapperV1.wrap(Page.getCardDesignerV2()))
            },
            Registration(HttpOptions.GET, "/frisco/validate") { ctx ->
                FriscoCore.validate()
                val configuration = ctx.queryParam("config")!!
                val response = runBlocking {
                    cueClient.sendForValidation(configuration)
                }
                ctx.html(response)
            },
        )
    }

    override fun name(): String {
        return Register::class.jvmName
    }

    // TODO
    fun handleCueErrors(responseBody: String) {}
}