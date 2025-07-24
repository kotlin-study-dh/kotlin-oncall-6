package oncall.view

import oncall.OnCallService
import oncall.domain.DayOfWeekType.WEEKDAY
import oncall.domain.Holiday

object OutputView {
    fun printErrorMessage(message: String?) {
        println("[ERROR] ${message ?: "Unknown error"}")
    }

    fun printSchedule(schedule: List<OnCallService.OnCallAssignment>) {
        for (assignment in schedule) {
            val (month, date, dayOfWeek, developer) = assignment
            println(
                "${month.displayName} $date ${dayOfWeek.abbreviation}${
                    if (Holiday.isHoliday(month, date) && dayOfWeek.type == WEEKDAY) "(Holiday)" else ""
                } ${developer.name}"
            )
        }
    }
}