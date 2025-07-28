package oncall.domain.calendar

import java.time.DayOfWeek
import java.time.Year

class Calendar(val month: Int, dayOfMonth: DayOfWeek) {

    private val days: List<Day>
    private val holidayResolver: HolidayResolver

    init {
        val endOfMoth = Year.of(BASE_YEAR).atMonth(month).atEndOfMonth().dayOfMonth
        days = (0 until endOfMoth).map { Day(month, it + 1, dayOfMonth.plus(it.toLong())) }
        holidayResolver = HolidayResolver()
    }

    val size
        get() = days.size

    operator fun get(index: Int) = days[index]

    fun retrieveWeekends(): List<Day> {
        return days.filter { it.isHoliday }
    }

    fun retrieveWeekdays(): List<Day> {
        return days.filter { it.isWeekDay }
    }

    companion object {
        const val BASE_YEAR = 2010
    }
}
