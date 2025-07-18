package oncall.shift

import oncall.worker.Worker

class ShiftWorkers(
    private val _shiftWorkers: List<Worker>,
) {
    val shiftWorkers: List<Worker>
        get() = _shiftWorkers.toList()

    init {
        require(_shiftWorkers.size == _shiftWorkers.toSet().size) {
            "Shift worker names must be unique."
        }
        require(_shiftWorkers.size >= MIN_SHIFT_WORKERS) {
            "There must be at least $MIN_SHIFT_WORKERS shift workers. Current shift worker count: ${_shiftWorkers.size}"
        }
    }

    companion object {
        private const val MIN_SHIFT_WORKERS = 5

        fun from(workerNames: List<String>): ShiftWorkers {
            val shiftWorkers = workerNames.map { Worker(it) }
            return ShiftWorkers(shiftWorkers)
        }
    }
}
