package oncall.shift

import oncall.shift.ShiftWorkers.NonWorkingDayShiftWorkers
import oncall.shift.ShiftWorkers.WorkingDayShiftWorkers
import oncall.util.LocalDateUtil
import oncall.util.isDifferentMonth
import oncall.util.isWorkingDay
import oncall.util.next
import oncall.util.previous
import oncall.worker.Worker
import java.time.LocalDate
import java.util.LinkedList
import java.util.Queue

class ShiftScheduler {
    fun create(
        workingDayShiftWorkers: WorkingDayShiftWorkers,
        nonWorkingDayShiftWorkers: NonWorkingDayShiftWorkers,
        month: Int,
        korDayOfWeek: String,
    ): Map<LocalDate, Worker> {
        // todo validate size
        val dayOfWeek = LocalDateUtil.getDayOfWeekFromKor(korDayOfWeek)
        val startDate = LocalDateUtil.resolveFirstMatchingDayOfWeek(month, dayOfWeek)
        return createSchedule(startDate, workingDayShiftWorkers, nonWorkingDayShiftWorkers)
    }

    private fun createSchedule(
        startDate: LocalDate,
        workingDayShiftWorkers: WorkingDayShiftWorkers,
        nonWorkingDayShiftWorkers: NonWorkingDayShiftWorkers,
    ): Map<LocalDate, Worker> {
        var now = startDate
        val schedule = LinkedHashMap<LocalDate, Worker>()
        val workingDayConsecutiveShiftWorkers: Queue<Worker> = LinkedList()
        val nonWorkingDayConsecutiveShiftWorkers: Queue<Worker> = LinkedList()

        while (!startDate.isDifferentMonth(now)) {
            val previousWorker = schedule[now.previous()]
            val nextWorker = if (now.isWorkingDay()) {
                getNextWorker(previousWorker, workingDayShiftWorkers, workingDayConsecutiveShiftWorkers)
            } else {
                getNextWorker(previousWorker, nonWorkingDayShiftWorkers, nonWorkingDayConsecutiveShiftWorkers)
            }
            schedule[now] = nextWorker
            now = now.next()
        }
        return schedule
    }

    private fun getNextWorker(
        previousWorker: Worker?,
        shiftWorkers: ShiftWorkers,
        consecutiveShiftWorkers: Queue<Worker>,
    ): Worker {
        if (previousWorker == null) {
            return shiftWorkers.getWorker()
        }
        return consecutiveShiftWorkers.peek().let {
            if (it == previousWorker) {
                shiftWorkers.getWorker()
            } else {
                consecutiveShiftWorkers.poll()
            }
        } ?: run {
            val nextWorker = shiftWorkers.getWorker()
            if (nextWorker == previousWorker) {
                consecutiveShiftWorkers.offer(nextWorker)
                shiftWorkers.getWorker()
            } else {
                nextWorker
            }
        }
    }
}
