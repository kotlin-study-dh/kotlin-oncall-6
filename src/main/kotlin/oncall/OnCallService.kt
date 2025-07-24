package oncall

import oncall.domain.DayOfWeek
import oncall.domain.DayOfWeekType
import oncall.domain.Developer
import oncall.domain.Developers
import oncall.domain.Holiday
import oncall.domain.Month
import oncall.domain.OnCallOrder

class OnCallService {
    data class OnCallAssignment(
        val month: Month,
        val date: Int,
        val dayOfWeek: DayOfWeek,
        val developer: Developer
    )

    fun schedule(
        month: Month,
        startDay: DayOfWeek,
        onCallOrder: OnCallOrder
    ): List<OnCallAssignment> {
        val totalDays = month.endDate
        return trySchedule(
            startDay, month, onCallOrder.weekdayOrder, onCallOrder.holidayOrder, totalDays
        )
    }

    private fun trySchedule(
        startDay: DayOfWeek,
        month: Month,
        weekdayOrder: Developers,
        holidayOrder: Developers,
        totalDays: Int
    ): List<OnCallAssignment> {
        var dayOfWeek = startDay
        val onCallAssignments = mutableListOf<OnCallAssignment>()
        val weekdayOrderState = weekdayOrder.copy()
        val holidayOrderState = holidayOrder.copy()
        for (date in 1..totalDays) {
            val isDayOff = dayOfWeek.type == DayOfWeekType.WEEKEND || Holiday.isHoliday(month, date)
            val nextDeveloper = if (isDayOff) {
                holidayOrderState.next()
            } else {
                weekdayOrderState.next()
            }
            if (onCallAssignments.lastOrNull()?.developer == nextDeveloper) {
                if (isDayOff) {
                    val newHolidayOrder = holidayOrderState.rearrange(nextDeveloper)
                    return trySchedule(startDay, month, weekdayOrder, newHolidayOrder, totalDays)
                } else {
                    val newWeekdayOrder = weekdayOrderState.rearrange(nextDeveloper)
                    return trySchedule(startDay, month, newWeekdayOrder, holidayOrder, totalDays)
                }
            }
            onCallAssignments.add(
                OnCallAssignment(
                    month,
                    date,
                    dayOfWeek,
                    nextDeveloper
                )
            )
            dayOfWeek = dayOfWeek.tomorrow()
        }
        return onCallAssignments
    }
}