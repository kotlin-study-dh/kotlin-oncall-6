package oncall.controller

import oncall.domain.calendar.Calendar
import oncall.domain.calendar.CustomDayOfWeek
import oncall.domain.member.Members
import oncall.domain.service.Scheduler
import oncall.view.Input
import oncall.view.Output

class Controller {

    fun handle() {
        val dayOfWeekAndMonth = Input.enterStartDate()
        val dayOfWeek = CustomDayOfWeek.from(dayOfWeekAndMonth.first).dayOfWeek
        val month = dayOfWeekAndMonth.second
        val calendar = Calendar(month, dayOfWeek)
        val weekdayOnCallMemberNames = Input.enterWeekdayOnCallMember()
        val holidayOnCallMemberNames = Input.enterHolidayOnCallMember()
        val weekdayOnCallMembers = Members(weekdayOnCallMemberNames)
        val holidayOnCallMembers = Members(holidayOnCallMemberNames)
        val scheduler = Scheduler(calendar, weekdayOnCallMembers, holidayOnCallMembers)
        val scheduledOrder = scheduler.schedule()
        Output.printMemberOrder(scheduledOrder, month)
    }
}