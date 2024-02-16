import java.io.File
import org.commonmark.parser.Parser as MarkdownParser
import org.commonmark.renderer.html.HtmlRenderer as MarkdownHtmlRenderer

val parser = MarkdownParser.builder().build()
val renderer = MarkdownHtmlRenderer.builder().build()

val file = File("/Users/anchorage/kotlin/website/src/main/resources/blog/entries/creating-my-website-from-chicken-scratch.md").readText()

val html = renderer.render(parser.parse(file))

println(html)
