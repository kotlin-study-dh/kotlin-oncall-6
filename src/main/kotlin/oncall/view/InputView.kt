package oncall.view

import camp.nextstep.edu.missionutils.Console
import oncall.domain.DayOfWeek
import oncall.domain.Developer
import oncall.domain.Developers
import oncall.domain.Month
import oncall.domain.OnCallOrder

object InputView {
    fun readStartDate(): Pair<Month, DayOfWeek> = retryOnFailure {
        print("Enter the month and start weekday for the on-call schedule> ")
        val monthAndDay = Console.readLine().split(",")
        Pair(Month.from(monthAndDay[0].toInt()), DayOfWeek.from(monthAndDay[1]))
    }

    fun readOnCallOrder(): OnCallOrder = retryOnFailure {
        val weekdayOrder = retryOnFailure {
            print("Enter weekday on-call order of developer nicknames> ")
            Developers(Console.readLine().split(",").map { Developer(it) })
        }
        val holidayOrder = retryOnFailure {
            print("Enter holiday on-call order of developer nicknames> ")
            Developers(Console.readLine().split(",").map { Developer(it) })
        }
        OnCallOrder(weekdayOrder, holidayOrder)
    }

    private fun <T> retryOnFailure(block: () -> T): T =
        runCatching { block() }
            .onFailure { e ->
                if (e is IllegalArgumentException) {
                    OutputView.printErrorMessage(e.message)
                    return retryOnFailure(block)
                }
            }.getOrThrow()

}