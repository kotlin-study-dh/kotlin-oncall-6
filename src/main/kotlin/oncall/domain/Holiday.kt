package oncall.domain

import java.time.Month

enum class Holiday(val month: Month, val day: Int) {

    NEW_YEARS_DAY(Month.JANUARY, 1),
    INDEPENDENCE_MOVEMENT_DAY(Month.MARCH, 1),
    CHILDRENS_DAY(Month.MAY, 5),
    MEMORIAL_DAY(Month.JUNE, 6),
    LIBERATION_DAY(Month.AUGUST, 15),
    NATIONAL_FOUNDATION_DAY(Month.OCTOBER, 3),
    HAN_GEUL_DAY(Month.OCTOBER, 9),
    CHRISTMAS_DAY(Month.DECEMBER, 25);

    companion object {
        fun getDaysOfHolidays(month: Month): Set<Int> = entries.filter { month == it.month }.map { it.day }.toSet()
    }
}
