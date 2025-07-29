package oncall.shift

import oncall.worker.Worker
import java.util.LinkedList
import java.util.Queue

sealed class ShiftWorkers(
    private val shiftWorkers: Queue<Worker>,
) {
    class WorkingDayShiftWorkers(shiftWorkers: Queue<Worker>) : ShiftWorkers(shiftWorkers) {
        companion object {
            fun from(workerNames: List<String>): WorkingDayShiftWorkers {
                val shiftWorkers = workerNames.map { Worker(it) }.toCollection(LinkedList())
                return WorkingDayShiftWorkers(shiftWorkers)
            }
        }
    }

    class NonWorkingDayShiftWorkers(shiftWorkers: Queue<Worker>) : ShiftWorkers(shiftWorkers) {
        companion object {
            fun from(workerNames: List<String>): NonWorkingDayShiftWorkers {
                val shiftWorkers = workerNames.map { Worker(it) }.toCollection(LinkedList())
                return NonWorkingDayShiftWorkers(shiftWorkers)
            }
        }
    }

    init {
        require(shiftWorkers.size == shiftWorkers.toSet().size) {
            "Shift worker names must be unique. ${this::class.simpleName}: ${shiftWorkers.map { it.name }}"
        }
        require(shiftWorkers.size in MIN_SHIFT_WORKERS..MAX_SHIFT_WORKERS) {
            "Unique shift workers must be between $MIN_SHIFT_WORKERS and $MAX_SHIFT_WORKERS. Invalid ${this::class.simpleName} size: ${shiftWorkers.size}."
        }
    }

    fun containsAll(other: ShiftWorkers): Boolean {
        return shiftWorkers.containsAll(other.shiftWorkers)
    }

    fun getWorker(): Worker {
        check(shiftWorkers.isNotEmpty()) {
            "There are no shift workers available."
        }
        val worker = shiftWorkers.poll()
        shiftWorkers.offer(worker)
        return worker
    }

    companion object {
        private const val MIN_SHIFT_WORKERS = 5
        private const val MAX_SHIFT_WORKERS = 35
    }
}
