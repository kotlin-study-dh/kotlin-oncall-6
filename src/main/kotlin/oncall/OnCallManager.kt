package oncall

import oncall.domain.Calender
import oncall.domain.OnCallWorkers
import oncall.domain.Workers
import oncall.view.printOnCallWorkers
import oncall.view.readHolidayOnCallWorkers
import oncall.view.readOnCallMonthAndStartDay
import oncall.view.readWeekdayOnCallWorkers
import java.time.Month

object OnCallManager {

    fun start() {
        val (month, day) = getOnCallMonthAndStartDay()
        val onCallWorkers = getOnCallWorkers()
        val nonConsecutiveRotationStrategy = NonConsecutiveRotationStrategy()
        val assignedOnCallWorkers = nonConsecutiveRotationStrategy.assign(
            onCallWorkers,
            Calender.of(Month.of(month), day)
        )
        printOnCallWorkers(assignedOnCallWorkers)
    }

    private fun getOnCallMonthAndStartDay() = retryLogic { readOnCallMonthAndStartDay() }

    private fun getOnCallWorkers() =
        retryLogic {
            val weekdayOnCallWorkers = Workers.of(readWeekdayOnCallWorkers())
            val holidayOnCallWorkers = Workers.of(readHolidayOnCallWorkers())
            OnCallWorkers(
                weekdayOnCallWorkers,
                holidayOnCallWorkers
            )
        }
}

private inline fun <T> retryLogic(crossinline block: () -> T): T {
    while (true) {
        try {
            return block()
        } catch (e: Exception) {
            println("[ERROR] ${e.message}")
        }
    }
}
