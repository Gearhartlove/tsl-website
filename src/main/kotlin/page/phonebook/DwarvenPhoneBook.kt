package page.phonebook

import core.HttpOptions
import core.Register
import core.Registration
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.html.*
import kotlinx.html.stream.createHTML

class DwarvenPhoneBook(private val mapper: ObjectMapper) : Register {


    data class Dwarf(val first: String, val last: String, val email: String)

    override fun registrations(): List<Registration> {
        return listOf(
            Registration(
                HttpOptions.GET,
                "/dwarvern-phone-book"
            ) { context ->
                val dwarfs = dwarfs()
                context.html(dwarfTableHtml(dwarfs))
            }
        )
    }

    override fun name(): String {
        return "Dwarf Phone Book"
    }

    private fun dwarfs(): List<Dwarf> {
        return List(Constants.COUNT) {
//            val rnd4 = floor(Math.random() * Constants.nm7.size).toInt()
//            val rnd5 = floor(Math.random() * Constants.nm8.size).toInt()
//            val rnd2 = floor(Math.random() * Constants.nm2.size).toInt()
//            val rnd3 = floor(Math.random() * Constants.nm3.size).toInt()
//            val rnd = floor(Math.random() * Constants.nm1.size).toInt()
//
//            val first = Constants.nm1[rnd] + Constants.nm2[rnd2] + Constants.nm3[rnd3]
//            val last = Constants.nm7[rnd4] + Constants.nm8[rnd5]
//            val email = "$first.$last@moria.com"
//
//            Dwarf(first, last, email)
            TODO()
        }
    }

    private fun dwarfTableHtml(dwarfs: List<Dwarf>): String {
        return createHTML().html {
            body {
                table {
                    tr {
                        th { +"Number" }
                        th { +"First Name" }
                        th { +"Last Name" }
                        th { +"Email" }
                    }
                    dwarfs.withIndex().map { (index, dwarf) ->
                        tr {
                            td {
                                +(index + 1).toString()
                            }
                            td {
                                +dwarf.first
                            }
                            td {
                                +dwarf.last
                            }
                            td {
                                +dwarf.email
                            }
                        }
                    }
                }
            }
        }
    }

    object Constants {
        val COUNT = 50

    }
}
