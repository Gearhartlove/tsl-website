import java.io.File

class Blogger {
    fun get(id: String): String {
        val path = "src/main/resources/blog/entries/$id.md"
        val file = File(path)
        return file.readText()
    }
}