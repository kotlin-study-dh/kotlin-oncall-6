package oncall.controller

import oncall.shift.ShiftScheduler
import oncall.shift.ShiftWorkers.NonWorkingDayShiftWorkers
import oncall.shift.ShiftWorkers.WorkingDayShiftWorkers
import oncall.view.InputView
import oncall.view.OutputView

class OnCallController(
    private val shiftScheduler: ShiftScheduler,
) {
    fun start() {
        val (month, korDayOfWeek) = retryOnFailure {
            InputView.readShiftMonthAndStartDayOfWeek()
        }
        val schedule = retryOnFailure {
            val workingDayShiftWorkers = getWorkingDayShiftWorkers()
            val nonWorkingDayShiftWorkers = getNonWorkingDayShiftWorkers()
            shiftScheduler.create(workingDayShiftWorkers, nonWorkingDayShiftWorkers, month, korDayOfWeek)
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
