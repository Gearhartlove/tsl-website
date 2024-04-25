package page

import page.index.Page as IndexPage

object WrapperV1 {
    fun wrap(content: String): String {
        return """
            ${IndexPage.head}
            ${IndexPage.navDiv}
            $content
        """.trimIndent()
    }
}