package oncall.view

import oncall.util.isPublicHoliday
import oncall.util.korDayOfWeek
import oncall.worker.Worker
import java.time.LocalDate

object OutputView {
    private const val ERROR_MESSAGE_PREFIX = "[ERROR] "
    private const val PUBLIC_HOLIDAY = "(휴일)"

    fun printSchedule(schedule: Map<LocalDate, Worker>) {
        schedule.forEach { (date, worker) ->
            val korDayOfWeek = if (date.isPublicHoliday()) {
                date.korDayOfWeek() + PUBLIC_HOLIDAY
            } else {
                date.korDayOfWeek()
            }
            println("${date.month}월 ${date.dayOfMonth}일 $korDayOfWeek ${worker.name}")
        }
    }

    fun printErrorMessage(message: String?) {
        println("$ERROR_MESSAGE_PREFIX$message")
    }
}
