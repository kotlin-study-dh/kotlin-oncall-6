package oncall.worker

class Workers(
    private val _workers: List<Worker>,
) {
    val workers: List<Worker>
        get() = _workers.toList()

    init {
        require(_workers.size == _workers.toSet().size) {
            "Worker names must be unique."
        }
        require(_workers.size >= MIN_WORKERS) {
            "There must be at least $MIN_WORKERS workers. Current worker count: ${_workers.size}"
        }
    }

    companion object {
        private const val MIN_WORKERS = 5

        fun from(workerNames: List<String>): Workers {
            val workers = workerNames.map { Worker(it) }
            return Workers(workers)
        }
    }
}
