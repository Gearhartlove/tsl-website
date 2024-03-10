package phonebook.ancestries

import phonebook.Entity
import phonebook.Listings
import phonebook.Phonebook
import kotlin.random.Random

object Goblin : Listings {
    override fun listings(): List<Entity> = List(Phonebook.listings) {
        val nTp = Random.nextInt(8)
        val rnd2 = Random.nextInt(nm8.size)
        var rnd3 = Random.nextInt(nm12.size)

        val first = {
            var rnd = Random.nextInt(nm7.size)
            while (nm7[rnd].isEmpty()) {
                rnd = Random.nextInt(nm7.size)
            }
            while (nm12[rnd3].isEmpty()) {
                rnd3 = Random.nextInt(nm12.size)
            }
            nm7[rnd] + nm8[rnd2] + nm12[rnd3]
        }()

        val last = {
            val rnd = Random.nextInt(nm7.size)
            var rnd4 = Random.nextInt(nm9.size)
            while (nm9[rnd4] == nm7[rnd] || nm9[rnd4] == nm12[rnd3]) {
                rnd4 = Random.nextInt(nm9.size)
            }
            val rnd5 = Random.nextInt(nm10.size)
            if (nTp < 6) {
                nm7[rnd] + nm8[rnd2] + nm9[rnd4] + nm10[rnd5] + nm12[rnd3]
            } else {
                val rnd6 = Random.nextInt(nm8.size)
                var rnd7 = Random.nextInt(nm11.size)
                while (nm11[rnd7] == nm9[rnd4] || nm11[rnd7] == nm12[rnd3]) {
                    rnd7 = Random.nextInt(nm11.size)
                }
                if (nTp == 6) {
                    nm7[rnd] + nm8[rnd2] + nm9[rnd4] + nm8[rnd6] + nm11[rnd7] + nm10[rnd5] + nm12[rnd3]
                } else {
                    nm7[rnd] + nm8[rnd2] + nm11[rnd7] + nm8[rnd6] + nm9[rnd4] + nm10[rnd5] + nm12[rnd3]
                }
            }
        }()

        val email = "$first.$last@skullcrusher.com"

        Entity(first, last, email)
    }

    val nm1 = arrayOf(
        "",
        "",
        "",
        "",
        "",
        "b",
        "d",
        "f",
        "g",
        "gr",
        "j",
        "k",
        "kl",
        "kw",
        "m",
        "n",
        "q",
        "r",
        "s",
        "v",
        "w",
        "z"
    )
    val nm2 = arrayOf("ee", "y", "a", "e", "i", "a", "e", "i", "o")
    val nm3 = arrayOf(
        "b",
        "d",
        "g",
        "k",
        "l",
        "m",
        "n",
        "q",
        "sh",
        "t",
        "v",
        "x",
        "z",
        "b",
        "d",
        "g",
        "k",
        "l",
        "m",
        "n",
        "q",
        "sh",
        "t",
        "v",
        "x",
        "z",
        "b",
        "d",
        "g",
        "k",
        "l",
        "m",
        "n",
        "q",
        "sh",
        "t",
        "v",
        "x",
        "z",
        "lb",
        "ld",
        "lg",
        "lk",
        "lm",
        "ln",
        "lq",
        "lv",
        "lz",
        "nb",
        "nd",
        "ng",
        "nk",
        "nq",
        "nv",
        "nz",
        "shk",
        "shm",
        "shn",
        "shq",
        "shz",
        "tg",
        "tk",
        "tq",
        "tv",
        "tz",
        "xd",
        "xk",
        "xm",
        "xn",
        "xq",
        "xv",
        "xz",
        "zd",
        "zg",
        "zk",
        "zm",
        "zn",
        "zq",
        "zv",
        "zz"
    )
    val nm4 = arrayOf("ee", "ie", "a", "e", "i", "a", "e", "i", "a", "e", "i", "a", "e", "i", "o")
    val nm5 = arrayOf(
        "b",
        "bl",
        "d",
        "g",
        "k",
        "l",
        "m",
        "q",
        "r",
        "t",
        "v",
        "x",
        "z",
        "b",
        "bl",
        "d",
        "g",
        "k",
        "l",
        "m",
        "q",
        "r",
        "t",
        "v",
        "x",
        "z",
        "b",
        "bl",
        "d",
        "g",
        "k",
        "l",
        "m",
        "q",
        "r",
        "t",
        "v",
        "x",
        "z",
        "b",
        "bl",
        "d",
        "g",
        "k",
        "l",
        "m",
        "q",
        "r",
        "t",
        "v",
        "x",
        "z",
        "b",
        "bl",
        "d",
        "g",
        "k",
        "l",
        "m",
        "q",
        "r",
        "t",
        "v",
        "x",
        "z",
        "bb",
        "gb",
        "kb",
        "nb",
        "qb",
        "zb",
        "dg",
        "gg",
        "ng",
        "vg",
        "zg",
        "bl",
        "dl",
        "gl",
        "kl",
        "ql",
        "zl",
        "dm",
        "gm",
        "km",
        "mm",
        "nm",
        "qm",
        "vm",
        "zm",
        "br",
        "dr",
        "gr",
        "kr",
        "qr",
        "vr",
        "zr",
        "dt",
        "gt",
        "kt",
        "nt",
        "qt",
        "vt",
        "zt",
        "dx",
        "gx",
        "kx",
        "nx",
        "qx",
        "dz",
        "gz",
        "kz",
        "mz",
        "nz",
        "rz",
        "zz"
    )
    val nm6 =
        arrayOf("", "", "", "", "", "k", "kle", "l", "ld", "n", "nk", "nkle", "rd", "rt", "s", "t", "x", "xle", "z")
    val nm7 = arrayOf(
        "",
        "",
        "",
        "",
        "",
        "bl",
        "c",
        "f",
        "fl",
        "g",
        "gl",
        "gr",
        "k",
        "kl",
        "l",
        "m",
        "n",
        "p",
        "r",
        "s",
        "sl",
        "t",
        "tr",
        "tw"
    )
    val nm8 = arrayOf(
        "ee",
        "ui",
        "y",
        "a",
        "e",
        "i",
        "u",
        "a",
        "e",
        "i",
        "u",
        "a",
        "e",
        "i",
        "u",
        "a",
        "e",
        "i",
        "u",
        "a",
        "e",
        "i",
        "u"
    )
    val nm9 = arrayOf(
        "b",
        "d",
        "g",
        "k",
        "l",
        "m",
        "n",
        "nk",
        "p",
        "q",
        "s",
        "v",
        "x",
        "z",
        "zz",
        "b",
        "d",
        "g",
        "k",
        "l",
        "m",
        "n",
        "nk",
        "p",
        "q",
        "s",
        "v",
        "x",
        "z",
        "zz",
        "b",
        "d",
        "g",
        "k",
        "l",
        "m",
        "n",
        "nk",
        "p",
        "q",
        "s",
        "v",
        "x",
        "z",
        "zz",
        "gb",
        "gd",
        "gg",
        "gn",
        "gv",
        "gz",
        "lb",
        "ld",
        "lg",
        "lk",
        "lm",
        "ln",
        "lq",
        "lv",
        "lz",
        "mg",
        "mm",
        "mn",
        "mq",
        "mv",
        "mz",
        "nd",
        "ng",
        "nk",
        "nm",
        "nn",
        "nq",
        "nv",
        "nz",
        "pg",
        "pq",
        "pv",
        "pz",
        "sg",
        "sk",
        "sm",
        "sn",
        "sq",
        "sv",
        "sz",
        "xd",
        "xk",
        "xm",
        "xn",
        "xz",
        "zd",
        "zg",
        "zk",
        "zn",
        "zq",
        "zv"
    )
    val nm10 = arrayOf(
        "ee",
        "ie",
        "y",
        "o",
        "a",
        "e",
        "i",
        "a",
        "e",
        "i",
        "o",
        "a",
        "e",
        "i",
        "a",
        "e",
        "i",
        "o",
        "a",
        "e",
        "i",
        "a",
        "e",
        "i",
        "o",
        "a",
        "e",
        "i",
        "a",
        "e",
        "i"
    )
    val nm11 = arrayOf(
        "b",
        "bl",
        "d",
        "g",
        "k",
        "l",
        "m",
        "n",
        "q",
        "s",
        "t",
        "tw",
        "v",
        "w",
        "x",
        "z",
        "b",
        "bl",
        "d",
        "g",
        "k",
        "l",
        "m",
        "n",
        "q",
        "s",
        "t",
        "tw",
        "v",
        "w",
        "x",
        "z",
        "b",
        "bl",
        "d",
        "g",
        "k",
        "l",
        "m",
        "n",
        "q",
        "s",
        "t",
        "tw",
        "v",
        "w",
        "x",
        "z",
        "b",
        "bl",
        "d",
        "g",
        "k",
        "l",
        "m",
        "n",
        "q",
        "s",
        "t",
        "tw",
        "v",
        "w",
        "x",
        "z",
        "b",
        "bl",
        "d",
        "g",
        "k",
        "l",
        "m",
        "n",
        "q",
        "s",
        "t",
        "tw",
        "v",
        "w",
        "x",
        "z",
        "bbl",
        "kk",
        "nk",
        "zk",
        "dl",
        "gl",
        "kl",
        "ql",
        "zl",
        "gn",
        "kn",
        "mn",
        "nn",
        "qn",
        "vn",
        "zn",
        "gs",
        "ks",
        "ns",
        "qs",
        "zs",
        "gt",
        "kt",
        "nt",
        "qt",
        "gtw",
        "ktw",
        "ntw",
        "dw",
        "gw",
        "gx",
        "kx",
        "nx",
        "qx",
        "dz",
        "gz",
        "kz",
        "nz",
        "qz",
        "zz"
    )
    val nm12 = arrayOf(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "kle",
        "l",
        "ls",
        "nk",
        "nkle",
        "s",
        "tte",
        "x",
        "z",
        "zz"
    )
    val nm13 = arrayOf(
        "acid",
        "agile",
        "arid",
        "barren",
        "bent",
        "bland",
        "blind",
        "bolt",
        "boom",
        "bot",
        "bright",
        "brisk",
        "broken",
        "cheap",
        "clear",
        "coarse",
        "cog",
        "copper",
        "craft",
        "cut",
        "damp",
        "dead",
        "dull",
        "eager",
        "faint",
        "false",
        "far",
        "fast",
        "fickle",
        "fierce",
        "fix",
        "fiz",
        "fizz",
        "fizzle",
        "foam",
        "fuse",
        "gear",
        "giga",
        "glum",
        "gold",
        "grand",
        "grapple",
        "grease",
        "greasy",
        "greed",
        "grim",
        "grime",
        "ground",
        "haggle",
        "hard",
        "keen",
        "knee",
        "lazy",
        "leaf",
        "loose",
        "mad",
        "man",
        "mean",
        "mega",
        "money",
        "mud",
        "multi",
        "nift",
        "peddle",
        "pepper",
        "pick",
        "plain",
        "power",
        "rapid",
        "rash",
        "rocket",
        "rough",
        "rust",
        "salt",
        "salty",
        "sand",
        "scroll",
        "shadow",
        "sharp",
        "shift",
        "short",
        "shrill",
        "silver",
        "slick",
        "sly",
        "small",
        "smart",
        "smug",
        "spark",
        "stark",
        "steam",
        "strong",
        "top",
        "vex",
        "vivid",
        "wild",
        "wrench"
    )
    val nm14 = arrayOf(
        "band",
        "basher",
        "bead",
        "beam",
        "beast",
        "belt",
        "bit",
        "bite",
        "blade",
        "blast",
        "blaster",
        "bolt",
        "bomb",
        "bombs",
        "boot",
        "bottom",
        "brake",
        "brass",
        "bub",
        "bucket",
        "bulb",
        "burst",
        "button",
        "buttons",
        "cash",
        "charge",
        "chart",
        "cheek",
        "chin",
        "clamp",
        "coat",
        "coil",
        "collar",
        "cord",
        "crook",
        "digger",
        "dirt",
        "doc",
        "drive",
        "dust",
        "fang",
        "fault",
        "feet",
        "fight",
        "fingers",
        "fire",
        "flame",
        "flare",
        "flow",
        "fluke",
        "fuel",
        "fuse",
        "gear",
        "gift",
        "gleam",
        "gob",
        "grin",
        "grinder",
        "grubber",
        "guard",
        "hallow",
        "hammer",
        "head",
        "hire",
        "hold",
        "hook",
        "jolt",
        "kettle",
        "knob",
        "lock",
        "mask",
        "mind",
        "mine",
        "mix",
        "nose",
        "nozzle",
        "patch",
        "pinch",
        "pocket",
        "post",
        "pot",
        "racket",
        "rocket",
        "screw",
        "shatter",
        "shift",
        "shiv",
        "skimmer",
        "slice",
        "smile",
        "snap",
        "snipe",
        "spark",
        "sprocket",
        "steam",
        "steel",
        "tale",
        "task",
        "tongue",
        "tooth",
        "tweak",
        "twist",
        "twister",
        "volt",
        "watts",
        "well",
        "wick",
        "wizzle",
        "wrench"
    )
    val br = ""
}