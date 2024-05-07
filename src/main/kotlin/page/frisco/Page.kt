package page.frisco

import kotlinx.html.*
import kotlinx.html.stream.createHTML

object Page {
    val designerPlaceHolderConstant: String = """
        #goblin: {
        	name: string
        }
        gut: #goblin & {
            name: "gut"
        }
    """.trimIndent()

    fun getCardDesigner(): String {
        return createHTML().div {
            form {
                action = "/frisco/validate"
                p { +"cue configuration" }
                textArea {
                    id = "config"
                    name = "config"
                    rows = "20"
                    cols = "50"
                    + designerPlaceHolderConstant
                }
                br { }
                br { }
                input {
                    type = InputType.submit
                    value = "Submit Configuration"
                }
            }
        }
    }
}
