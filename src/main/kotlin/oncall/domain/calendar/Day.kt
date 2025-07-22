package oncall.domain.calendar

import java.time.DayOfWeek

data class Day(val month: Int, val day: Int, val dayOfWeek: DayOfWeek = DayOfWeek.MONDAY) {

    val holidayResolver: HolidayResolver = HolidayResolver()

    val isWeekend: Boolean
        get() = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY || holidayResolver.resolve(this)

    val isWeekDay: Boolean
        get() = dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY && !holidayResolver.resolve(this)
}
