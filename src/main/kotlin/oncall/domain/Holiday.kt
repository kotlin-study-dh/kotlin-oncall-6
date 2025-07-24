package oncall.domain

import oncall.domain.Month.*

enum class Holiday(val month: Month, val day: Int) {
    NEW_YEAR_DAY(JANUARY, 1),
    INDEPENDENCE_MOVEMENT_DAY(MARCH, 1),
    CHILDREN_DAY(MAY, 5),
    MEMORIAL_DAY(JUNE, 6),
    LIBERATION_DAY(AUGUST, 15),
    NATIONAL_FOUNDATION_DAY(OCTOBER, 3),
    HANGEUL(OCTOBER, 9),
    CHRISTMAS_DAY(DECEMBER, 25),
    ;

    companion object {
        fun isHoliday(month: Month, day: Int): Boolean {
            return entries.any { it.month == month && it.day == day }
        }
    }
}