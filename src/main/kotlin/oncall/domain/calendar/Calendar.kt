package oncall.domain.calendar

import java.time.DayOfWeek
import java.time.Year

class Calendar(month: Int, dayOfMonth: DayOfWeek) {

    val days: List<Day>

    init {
        val endOfMoth = Year.of(BASE_YEAR).atMonth(month).atEndOfMonth().dayOfMonth
        days = (0 until endOfMoth).map { Day(month, it + 1, dayOfMonth.plus(it.toLong())) }
    }

    fun retrieveWeekends(): List<Day> {
        return days.filter { it.isWeekend }
    }

    fun retrieveWeekdays(): List<Day> {
        return days.filter { it.isWeekDay }
    }

    companion object {
        const val BASE_YEAR = 2010
    }
}
