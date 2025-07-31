package oncall.domain.service

import oncall.domain.calendar.Calendar
import oncall.domain.member.Members
import oncall.dto.ScheduledResponse

class Scheduler(private val calendar: Calendar, weekdayOrder: Members, holidayOrder: Members) {

    private val holidayQueue: OnCallWaitingQueue = OnCallWaitingQueue(holidayOrder)
    private val weekdayQueue: OnCallWaitingQueue = OnCallWaitingQueue(weekdayOrder)

    init {
        require(weekdayOrder.compareInAnyOrder(holidayOrder)) {
            "weekday member and holiday members are not equal"
        }
    }

    fun schedule(): List<ScheduledResponse> {
        val orders = mutableListOf<ScheduledResponse>()
        repeat(calendar.size) {
            val queueForToday = if (calendar[it].isHoliday) holidayQueue else weekdayQueue
            orders.add(
                ScheduledResponse(
                    queueForToday.next(orders.lastOrNull()?.name ?: NO_ONE_ASSIGNMENT),
                    calendar[it]
                )
            )

        }
        return orders.toList()
    }

    companion object {
        const val NO_ONE_ASSIGNMENT = "-"
    }
}