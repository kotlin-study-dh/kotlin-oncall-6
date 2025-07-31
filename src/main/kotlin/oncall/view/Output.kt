package oncall.view

import oncall.dto.ScheduledResponse

object Output {

    fun printMemberOrder(orders: List<ScheduledResponse>, month: Int) {
        orders.forEachIndexed { day, order -> println("${month}월 ${day + 1}일 ${order.dayOfWeekAsText} ${order.note}${order.name}") }
    }
}