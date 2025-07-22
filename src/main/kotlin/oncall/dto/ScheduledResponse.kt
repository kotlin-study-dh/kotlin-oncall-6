package oncall.dto

import oncall.domain.calendar.CustomDayOfWeek
import oncall.domain.calendar.Day

class ScheduledResponse(val name: String, day: Day) {

    val dayOfWeekAsText = CustomDayOfWeek.from(day.dayOfWeek).text
    val note = generateLegalHolidayFormat(day)

    private fun generateLegalHolidayFormat(day: Day): String {
        if (day.isLegalHoliday) {
            return "(휴일)"
        }
        return ""
    }
}