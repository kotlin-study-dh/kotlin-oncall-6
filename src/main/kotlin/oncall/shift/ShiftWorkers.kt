package oncall.shift

import oncall.worker.Worker
import java.util.LinkedList
import java.util.Queue

sealed class ShiftWorkers(
    private val _shiftWorkers: Queue<Worker>,
) {
    val shiftWorkers: List<Worker>
        get() = _shiftWorkers.toList()
    val size: Int
        get() = _shiftWorkers.size

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
        require(_shiftWorkers.size == _shiftWorkers.toSet().size) {
            "Shift worker names must be unique. ${this::class.simpleName}: ${_shiftWorkers.map { it.name }}"
        }
        require(_shiftWorkers.size >= MIN_SHIFT_WORKERS) {
            "There must be at least $MIN_SHIFT_WORKERS shift workers. ${this::class.simpleName}: ${_shiftWorkers.map { it.name }}"
        }
    }

    fun getWorker(): Worker {
        check(_shiftWorkers.isNotEmpty()) {
            "There are no shift workers available."
        }
        val worker = _shiftWorkers.poll()
        _shiftWorkers.offer(worker)
        return worker
    }

    companion object {
        private const val MIN_SHIFT_WORKERS = 2
    }
}
