package oncall.domain

enum class Month(val displayName: String, val value: Int, val endDate: Int) {
    JANUARY("January", 1, 31),
    FEBRUARY("February", 2, 28),
    MARCH("March", 3, 31),
    APRIL("April", 4, 30),
    MAY("May", 5, 31),
    JUNE("June", 6, 30),
    JULY("July", 7, 31),
    AUGUST("August", 8, 31),
    SEPTEMBER("September", 9, 30),
    OCTOBER("October", 10, 31),
    NOVEMBER("November", 11, 30),
    DECEMBER("December", 12, 31),
    ;


    companion object {
        private val valueToMonth: Map<Int, Month> = entries.associateBy { it.value }
        fun from(value: Int): Month =
            valueToMonth[value] ?: throw IllegalArgumentException("Invalid month value: $value")
    }
}