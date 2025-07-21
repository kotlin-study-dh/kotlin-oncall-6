package oncall.domain

import java.time.DayOfWeek
import java.time.Month

class Calender(
    val month: Month,
    val totalDaysInMonth: Int,
    val dayOfStartMonth: DayOfWeek
) {

    fun calculateDayOfWeek(date: Int): DayOfWeek {
        require(date in 1..totalDaysInMonth) {
            throw IllegalArgumentException("Invalid date $date")
        }

        return dayOfStartMonth.plus((date - 1).toLong())
            ?: throw IllegalArgumentException("Day of start month $date is not valid")
    }

    fun calculateWeekend() = (1..totalDaysInMonth).filter {
        calculateDayOfWeek(it) == DayOfWeek.SATURDAY
                || calculateDayOfWeek(it) == DayOfWeek.SUNDAY
    }.toSet()

    companion object {
        fun of(month: Month, dayOfStartMonth: DayOfWeek): Calender {
            val totalDaysInMonth = when (month) {
                Month.JANUARY, Month.MARCH, Month.MAY,
                Month.JULY, Month.AUGUST, Month.OCTOBER, Month.DECEMBER -> 31

                Month.APRIL, Month.JUNE, Month.SEPTEMBER, Month.NOVEMBER -> 30
                Month.FEBRUARY -> 28
            }

            return Calender(month, totalDaysInMonth, dayOfStartMonth)
        }
    }
}
