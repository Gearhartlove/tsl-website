package page.square

import clojure.java.api.Clojure
import clojure.lang.IFn
import core.HttpOptions
import core.Register
import core.Registration
import kotlin.reflect.jvm.jvmName

class Square: Register {
    override fun registrations(): List<Registration> {
        return listOf(
            Registration(
                HttpOptions.GET,
                "/square/{number}"
            ) { ctx ->
                try {
                    val number = ctx.pathParam("number").toDouble()

                    val require: IFn = Clojure.`var`("clojure.core", "require")
                    require.invoke(Clojure.read("square.core"))

                    val square: IFn = Clojure.`var`("square.core", "square")

                    val result = square.invoke(number)

                    ctx.status(200).result(result.toString())
                } catch (e: NumberFormatException) {
                    ctx.status(400).result("Input must be a valid number.")
                }
            }
        )
    }

    override fun name(): String {
        return Square::class.jvmName
    }
}