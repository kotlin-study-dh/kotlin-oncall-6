package oncall.domain.calendar

import java.time.DayOfWeek

data class Day(val month: Int, val day: Int, val dayOfWeek: DayOfWeek) {

    val isWeekend: Boolean
        get() = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY

    val isWeekDay: Boolean
        get() = dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY
}
