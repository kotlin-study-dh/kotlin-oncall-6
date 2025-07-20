package oncall

import oncall.controller.OnCallController
import oncall.shift.ShiftScheduler

fun main() {
    val onCallController = OnCallController(ShiftScheduler())
    onCallController.start()
}
