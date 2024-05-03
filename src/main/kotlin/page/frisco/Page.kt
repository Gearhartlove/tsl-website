package page.frisco

object Page {
    fun getCardDesigner(): String {
        return """
            <form action="/frisco-publish">
              <p>Schema</p>
              <textarea id="w3review" name="w3review" rows="20" cols="50">
              
              </textarea>
              <br>
              <p>Definitions</p>
              <textarea id="w3review" name="w3review" rows="20" cols="50"></textarea>
              <br>
              <br>
              <input type="submit" value="Submit">
            </form>
        """.trimIndent()
    }
}
