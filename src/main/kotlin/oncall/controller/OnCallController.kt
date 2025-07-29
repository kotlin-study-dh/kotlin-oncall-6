package oncall.controller

import oncall.shift.ShiftScheduler
import oncall.shift.ShiftWorkers.NonWorkingDayShiftWorkers
import oncall.shift.ShiftWorkers.WorkingDayShiftWorkers
import oncall.util.LocalDateUtil
import oncall.view.InputView
import oncall.view.OutputView

class OnCallController(
    private val shiftScheduler: ShiftScheduler,
) {
    fun start() {
        val startDate = retryOnFailure {
            val (month, korDayOfWeek) = InputView.readShiftMonthAndStartDayOfWeek()
            val dayOfWeek = LocalDateUtil.getDayOfWeekFromKor(korDayOfWeek)
            LocalDateUtil.resolveFirstMatchingDayOfWeek(month, dayOfWeek)
        }
        val schedule = retryOnFailure {
            val workingDayShiftWorkers = getWorkingDayShiftWorkers()
            val nonWorkingDayShiftWorkers = getNonWorkingDayShiftWorkers()
            shiftScheduler.create(workingDayShiftWorkers, nonWorkingDayShiftWorkers, startDate)
        }
        OutputView.printSchedule(schedule)
    }

    private fun getWorkingDayShiftWorkers(): WorkingDayShiftWorkers {
        val workingDayShiftWorkerNames = InputView.readWorkingDayShiftWorkerNames()
        return WorkingDayShiftWorkers.from(workingDayShiftWorkerNames)
    }

    private fun getNonWorkingDayShiftWorkers(): NonWorkingDayShiftWorkers {
        val nonWorkingDayShiftWorkerNames = InputView.readNonWorkingDayShiftWorkerNames()
        return NonWorkingDayShiftWorkers.from(nonWorkingDayShiftWorkerNames)
    }

    private fun <T> retryOnFailure(block: () -> T): T {
        while (true) {
            try {
                return block()
            } catch (e: IllegalArgumentException) {
                OutputView.printErrorMessage(e.message)
            }
        }
    }
}
