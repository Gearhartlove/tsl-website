package page.index

import kotlinx.html.*
import kotlinx.html.stream.createHTML


object Page {
    val head = createHTML().head {
        meta { charset = "UTF-16" }
        title { +"Two Shots Later" }
        script { src = "https://unpkg.com/htmx.org@1.9.10" }
        link {
            rel = "stylesheet"
            type = "text/css"
            href = "styles.css"
        }
        meta {
            name = "viewport"
            content = "width=device-width, initial-scale=1"
        }
    }

    val navDiv = createHTML().div {
        id = "navDiv"
        classes = setOf("container", "nav")

        a(href = "/") { +"Home" }
        a(href = "/blogger") { +"Blog" }
        a(href = "/resume") { +"Résumé" }
    }

    private val content = createHTML().div {
        classes = setOf("squish")
        id = "content"
        img {
            src = "/assets/alpine_adventure.jpg"
            alt = "skiing picture"
        }
        p {
            +"""
                |Howdy! My name is Kristoff and I'm happy your here! I'm a backend software engineer based in Montana. I'm out of office right now, probably playing Magic the Gathering, skiing at Crosscut, or bouldering a project v3, but you can find many of my toy projects and scrappy thoughts here.
            """.trimMargin()
        }
        p {
            style = "font-size: x-small;"
            +"Also, this website is self created and self hosted so expect some spelling errors and a backender's sense of style 😅. Cheers!"
        }
    }

    val html = """
        $head
        $navDiv
        $content
    """.trimIndent()
}
