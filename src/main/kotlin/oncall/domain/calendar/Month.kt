package oncall.domain.calendar

enum class Month(val value: Int, val endOfDay: Int) {

    JANUARY(1, 31),
    FEBRUARY(2, 28),
    MARCH(3, 31),
    APRIL(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JULY(7, 31),
    AUGUST(8, 31),
    SEPTEMBER(9, 30),
    OCTOBER(10, 31),
    NOVEMBER(11, 30),
    DECEMBER(12, 31);

    companion object {
        fun from(value: Int): Month {
            require(value in 1..12) {
                "Month value must be between 1 and 12"
            }
            
            return Month.entries
                .first { it.value == value }
        }
    }
}