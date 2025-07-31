package oncall.dto

import oncall.domain.calendar.CustomDayOfWeek
import oncall.domain.calendar.Day
import oncall.domain.member.Member

class ScheduledResponse(member: Member, day: Day) {

    val dayOfWeekAsText = CustomDayOfWeek.from(day.dayOfWeek).text
    val note = generateLegalHolidayFormat(day)
    val name = member.name
    
    private fun generateLegalHolidayFormat(day: Day): String {
        if (day.isLegalHoliday) {
            return "(휴일)"
        }
        return ""
    }
}