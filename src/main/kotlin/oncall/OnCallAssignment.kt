package oncall

import oncall.domain.Worker
import java.time.DayOfWeek
import java.time.Month

data class DailyOnCallAssignment(val worker: Worker, val onCallDate: OnCallDate)
data class OnCallDate(val month: Month, val day: Int, val dayOfWeek: DayOfWeek)
