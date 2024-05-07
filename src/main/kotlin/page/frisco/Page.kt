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

    fun getCardDesignerV2(): String {
        return """
        <textarea id = "config"
            name = "config"
            rows = 20
            cols = 50 
            hx-get="/frisco/validate"
            hx-trigger="input changed delay:500ms, search"
            hx-target="#validation-results">
            
            $designerPlaceHolderConstant
            
        </textarea>
        <br>
        <br>
        <div id="validation-results">
        </div>
        """.trimIndent()
    }
}
