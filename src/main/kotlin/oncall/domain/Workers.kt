package oncall.domain

class Workers(val workers: ArrayDeque<Worker>) {

    init {
        require(workers.size in 5..35) {
            throw IllegalArgumentException("workers must be in range 5 to 35")
        }
        require(workers.map { it.name }.distinct().size == workers.size) {
            throw IllegalArgumentException("worker's name must be distinct")
        }
    }

    fun getAssignedWorkerForDay(lastAssignedWorker: Worker?): Worker {
        val candidate = workers.firstOrNull() ?: throw IllegalStateException("Worker must not be empty")

        if (candidate == lastAssignedWorker) {
            val consecutiveWorker = workers.removeFirst()
            workers.addLast(consecutiveWorker)

            val nextWorker = getNextWorkerAndRotate()
            val temporaryWorker = Worker(consecutiveWorker.name, true)
            workers.addFirst(temporaryWorker)
            return nextWorker
        } else {
            return getNextWorkerAndRotate()
        }
    }

    fun getNextWorkerAndRotate(): Worker {
        val worker = workers.removeFirstOrNull() ?: throw IllegalStateException("Worker must not be empty")
        if(!worker.isTemporary) workers.addLast(worker)
        return worker
    }

    companion object {
        fun of(workerNames: List<String>): Workers {
            val workers = ArrayDeque(workerNames.map { Worker(Name(it)) })
            return Workers(workers)
        }
    }
}
