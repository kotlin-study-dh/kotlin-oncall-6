package oncall.domain.calendar

import java.time.DayOfWeek

class Calendar(val month: Int, dayOfMonth: DayOfWeek) {

    private val days: List<Day>

    init {
        val endOfMoth = Month.from(month).endOfDay
        days = (0 until endOfMoth).map { Day(month, it + 1, dayOfMonth.plus(it.toLong())) }
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
