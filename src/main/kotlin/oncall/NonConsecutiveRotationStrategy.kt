package oncall

import oncall.domain.Calender
import oncall.domain.Holiday
import oncall.domain.OnCallWorkers
import oncall.domain.Worker

class NonConsecutiveRotationStrategy : OnCallStrategy {

    override fun assign(
        onCallWorkers: OnCallWorkers,
        calender: Calender
    ): List<DailyOnCallAssignment> {
        val holidays = (Holiday.getDaysOfHolidays(calender.month) + calender.calculateWeekend()).toSet()
        var primaryWorker: Worker? = null

        return (1..calender.totalDaysInMonth).map { dayOfMonth ->
            val currentDayOfWeek = calender.calculateDayOfWeek(dayOfMonth)
            val isOnHoliday = holidays.contains(dayOfMonth)

            val assignedWorker = if (isOnHoliday) {
                onCallWorkers.holidayWorkers.getAssignedWorkerForDay(primaryWorker)
            } else {
                onCallWorkers.weekdayWorkers.getAssignedWorkerForDay(primaryWorker)
            }

            primaryWorker = assignedWorker

            DailyOnCallAssignment(
                assignedWorker,
                OnCallDate(calender.month, dayOfMonth, currentDayOfWeek)
            )
        }
    }
}
