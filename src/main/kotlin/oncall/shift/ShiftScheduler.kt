package oncall.shift

import oncall.shift.ShiftWorkers.NonWorkingDayShiftWorkers
import oncall.shift.ShiftWorkers.WorkingDayShiftWorkers
import oncall.util.isSameMonth
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
        startDate: LocalDate,
    ): Map<LocalDate, Worker> {
        validateShiftWorkersSize(workingDayShiftWorkers, nonWorkingDayShiftWorkers)
        var now = startDate
        val schedule = LinkedHashMap<LocalDate, Worker>()
        val workingDayConsecutiveShiftWorkers: Queue<Worker> = LinkedList()
        val nonWorkingDayConsecutiveShiftWorkers: Queue<Worker> = LinkedList()
        while (startDate.isSameMonth(now)) {
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

    private fun validateShiftWorkersSize(
        workingDayShiftWorkers: WorkingDayShiftWorkers,
        nonWorkingDayShiftWorkers: NonWorkingDayShiftWorkers,
    ) {
        workingDayShiftWorkers.shiftWorkers.union(nonWorkingDayShiftWorkers.shiftWorkers).also {
            require(it.size in MIN_SHIFT_WORKERS..MAX_SHIFT_WORKERS) {
                "Unique shift workers must be between $MIN_SHIFT_WORKERS and $MAX_SHIFT_WORKERS. Invalid shift worker size: ${it.size}."
            }
        }
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

    companion object {
        private const val MIN_SHIFT_WORKERS = 5
        private const val MAX_SHIFT_WORKERS = 35
    }
}
