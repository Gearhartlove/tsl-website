import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import java.net.URLEncoder

class CueClient {
    val url = "http://localhost:49153/cue"
    val client = HttpClient(CIO)

    suspend fun sendForValidation(configuration: String): String {
        val configurationEncoded = URLEncoder.encode(configuration, "UTF-8")

        val urlWithParams = url + "?config=" + configurationEncoded
        println("send for validation url: $urlWithParams")

        val response = client.get(urlWithParams)
        println(response.status)
        println(response.bodyAsText())

        return response.bodyAsText()
    }
}