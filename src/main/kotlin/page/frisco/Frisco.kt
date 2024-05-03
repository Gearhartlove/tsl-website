package page.frisco

import core.HttpOptions
import core.Register
import core.Registration
import kotlin.reflect.jvm.jvmName

class Frisco: Register {
    override fun registrations(): List<Registration> {
        return listOf(
            Registration(
                HttpOptions.GET,
                "/frisco"
            ) { ctx ->
                ctx.html(Page.getCardDesigner())
            }
        )
    }

    override fun name(): String {
        return Register::class.jvmName
    }

    val schema = """
        // __    ___         __          _
        /// _\  / __\ /\  /\/__\/\/\    /_\
        //\ \  / /   / /_/ /_\ /    \  //_\\
        //_\ \/ /___/ __  //__/ /\/\ \/  _  \
        //\__/\____/\/ /_/\__/\/    \/\_/ \_/

        // SCHEMA
        #weapon: {
            name!: string
            damage!: >0 & <10
            speed!: >0 & <10
        }

        #goblin: {
            weapon!: weapon
            name!: string
        }
    """.trimIndent()

    val definitions = """
        //    ___  __  ________    __ _____  __________  ___    __  __
        //   /   \/__\/ __\_   \/\ \ \\_   \/__   \_   \/___\/\ \ \/ _\
        //  / /\ /_\ / _\  / /\/  \/ / / /\/  / /\// /\//  //  \/ /\ \
        // / /_///__/ / /\/ /_/ /\  /\/ /_   / //\/ /_/ \_// /\  / _\ \
        ///___,'\__/\/  \____/\_\ \/\____/   \/ \____/\___/\_\ \/  \__/

        gut: #goblin & {
        	weapon: {
        		name: "Ceremonial dagger"
        		damage: 3
        		speed: 10
        	}
        	name: "gut"
        }
    """.trimIndent()
}