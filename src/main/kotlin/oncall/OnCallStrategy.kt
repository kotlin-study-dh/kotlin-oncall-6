package oncall

import oncall.domain.Calender
import oncall.domain.OnCallWorkers

interface OnCallStrategy {

    fun assign(
        onCallWorkers: OnCallWorkers,
        calender: Calender
    ): List<DailyOnCallAssignment>
}
