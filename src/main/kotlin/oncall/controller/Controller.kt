package oncall.controller

import oncall.domain.calendar.Calendar
import oncall.domain.calendar.CustomDayOfWeek
import oncall.domain.member.Members
import oncall.domain.service.Scheduler
import oncall.view.Input
import oncall.view.Output

class Controller {

    fun handle() {
        val calendar = retryCallback { createCalendar() }
        val weekdayOnCallMembers = retryCallback { createWeekdayMember() }
        val holidayOnCallMembers = retryCallback { createHolidayMember() }
        val scheduler = Scheduler(calendar, weekdayOnCallMembers, holidayOnCallMembers)
        val scheduledOrder = scheduler.schedule()
        Output.printMemberOrder(scheduledOrder, calendar.month)
    }

    private fun createCalendar(): Calendar {
        val dayOfWeekAndMonth = Input.enterStartDate()
        val dayOfWeek = CustomDayOfWeek.from(dayOfWeekAndMonth.first).dayOfWeek
        val month = dayOfWeekAndMonth.second
        return Calendar(month, dayOfWeek)
    }

    private fun createWeekdayMember(): Members {
        val weekdayOnCallMemberNames = Input.enterWeekdayOnCallMember()
        return Members(weekdayOnCallMemberNames)
    }

    private fun createHolidayMember(): Members {
        val holidayOnCallMemberNames = Input.enterHolidayOnCallMember()
        return Members(holidayOnCallMemberNames)
    }

    private fun <T> retryCallback(callback: () -> T): T {
        try {
            return callback()
        } catch (e: Exception) {
            println("[ERROR] ${e.message}")
            println()
            return retryCallback { callback() }
        }
    }
}