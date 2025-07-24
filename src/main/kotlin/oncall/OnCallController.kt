package oncall

import oncall.view.InputView
import oncall.view.OutputView

class OnCallController(
    private val onCallService: OnCallService
) {
    fun run() {
        val (month, dayOfWeek) = InputView.readStartDate()
        val onCallOrder = InputView.readOnCallOrder()
        val schedule = onCallService.schedule(month, dayOfWeek, onCallOrder)
        OutputView.printSchedule(schedule)
    }
}