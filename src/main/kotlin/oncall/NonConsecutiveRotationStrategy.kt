package oncall

import oncall.domain.Calender
import oncall.domain.Holiday
import oncall.domain.Worker
import oncall.domain.Workers

class NonConsecutiveRotationStrategy : OnCallStrategy {

    override fun assign(
        weekdayWorkers: Workers,
        holidayWorkers: Workers,
        calender: Calender
    ): List<DailyOnCallAssignment> {
        val holidays = (Holiday.getDaysOfHolidays(calender.month) + calender.calculateWeekend()).toSet()
        var primaryWorker: Worker? = null

        return (1..calender.totalDaysInMonth).map { dayOfMonth ->
            val currentDayOfWeek = calender.calculateDayOfWeek(dayOfMonth)
            val isOnHoliday = holidays.contains(dayOfMonth)

            val assignedWorker = if (isOnHoliday) {
                holidayWorkers.getAssignedWorkerForDay(primaryWorker)
            } else {
                weekdayWorkers.getAssignedWorkerForDay(primaryWorker)
            }

            primaryWorker = assignedWorker

            DailyOnCallAssignment(
                assignedWorker,
                OnCallDate(calender.month, dayOfMonth, currentDayOfWeek)
            )
        }
    }
}
