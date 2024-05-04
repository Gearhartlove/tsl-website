package page.blog

enum class Month {
    JANUARY,
    FEBRUARY,
    MARCH,
    APRIL,
    MAY,
    JUNE,
    JULY,
    AUGUST,
    SEPTEMBER,
    OCTOBER,
    NOVEMBER,
    DECEMBER
}

data class BlogTitle(
    val internalTitle: String,
    val publicTitle: String,
    val month: Month? = null,
    val day: Int? = null,
    val year: Int? = null
)
