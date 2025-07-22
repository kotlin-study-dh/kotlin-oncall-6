package oncall.domain.service

import oncall.domain.calendar.Calendar
import oncall.domain.member.Members
import oncall.dto.ScheduledResponse

class Scheduler(private val calendar: Calendar, weekdayOrder: Members, holidayOrder: Members) {

    private val holidayQueue: OnCallWaitingQueue = OnCallWaitingQueue(holidayOrder)
    private val weekdayQueue: OnCallWaitingQueue = OnCallWaitingQueue(weekdayOrder)

    fun schedule(): List<ScheduledResponse> {
        val orders = mutableListOf<ScheduledResponse>()
        for (i in 1..calendar.size) {
            if (calendar[i - 1].isHoliday) {
                orders.add(ScheduledResponse(holidayQueue.next(orders.lastOrNull()?.name ?: ""), calendar[i - 1]))
            }
            if (calendar[i - 1].isWeekDay) {
                orders.add(ScheduledResponse(weekdayQueue.next(orders.lastOrNull()?.name ?: ""), calendar[i - 1]))
            }
        }
        return orders.toList()
    }
}