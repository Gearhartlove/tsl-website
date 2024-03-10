package phonebook

import Register
import Registration
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import phonebook.ancestries.Dwarf
import phonebook.ancestries.Elf
import phonebook.ancestries.Goblin

class Phonebook : Register {
    companion object {
        val listings = 100
        val page = createHTML().html {
            body {
                ul {
                    li {
                        a {
                            href = "/phonebook/dwarfs"
                            +"Dwarfs"
                        }
                    }
                    li {
                        a {
                            href = "/phonebook/elves"
                            +"Elves"
                        }
                    }
                    li {
                        a {
                            href = "/phonebook/goblins"
                            +"Goblins"
                        }
                    }
                }
            }
        }
    }

    override fun registrations() = listOf(
        Registration(
            HttpOptions.GET,
            "/phonebook"
        ) { context ->
          context.html(page)
        },
        Registration(
            HttpOptions.GET,
            "/phonebook/{ancestry}"
        ) { context ->
            val ancestryPhonebook = when (context.pathParam("ancestry")) {
                "dwarfs" -> Dwarf.listings()
                "elves" -> Elf.listings()
                "goblins" -> Goblin.listings()
                else -> listOf()
            }

            val table = createHTML().html {
                body {
                    table {
                        tr {
                            th { +"Number" }
                            th { +"First Name" }
                            th { +"Last Name" }
                            th { +"Email" }
                        }
                        ancestryPhonebook.withIndex().map { (index, entity) ->
                            tr {
                                td {
                                    +(index + 1).toString()
                                }
                                td {
                                    +entity.first
                                }
                                td {
                                    +entity.last
                                }
                                td {
                                    +entity.email
                                }
                            }

                        }
                    }
                }
            }

            context.html(table)
        }
    )

    override fun name() = "Phonebook"
}