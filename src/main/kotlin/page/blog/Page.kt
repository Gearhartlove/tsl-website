package page.blog

import Main
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import org.commonmark.parser.Parser as MarkdownParser
import org.commonmark.renderer.html.HtmlRenderer as MarkdownHtmlRenderer

object Page {
    fun entries(blogTitles: List<BlogTitle>): String {
        return createHTML().div {
            classes = setOf("column-container")
            h3 {
                id = "website"
                a {
                    classes = setOf("zola-anchor")
                    href = "#website"
                    +"#"
                }
                +" Website"
            }
            ul {
                blogTitles.forEach { title ->
                    li {
                        a {
                            href = "/blogger/${title.internalTitle}"
                            +title.publicTitle
                        }
                        if (title.month != null && title.year != null && title.day != null) {
                            p {
                                style = "font-size:10px;"
                                em {

                                    +"${title.month} ${title.day}, ${title.year}"
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun blog(blog: String, mdParser: MarkdownParser, mdHtmlRenderer: MarkdownHtmlRenderer): String {
        val readText = Main::class.java.getResource("blog/$blog.md").readText()
        val parsed = mdParser.parse(readText)
        val renderedHTML = mdHtmlRenderer.render(parsed)

        return """
            <div class="squish">
                $renderedHTML
            </div>
        """.trimIndent()
    }
}