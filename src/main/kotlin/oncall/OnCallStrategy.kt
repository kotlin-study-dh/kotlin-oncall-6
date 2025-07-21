package oncall

import oncall.domain.Calender
import oncall.domain.Workers

interface OnCallStrategy {

    fun assign(
        weekdayWorkers: Workers,
        holidayWorkers: Workers,
        calender: Calender
    ): List<DailyOnCallAssignment>
}
