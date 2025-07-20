package oncall.domain

data class Workers(val workers: ArrayDeque<Worker>) {

    init {
        require(workers.size in 5..35) {
            throw IllegalArgumentException("workers must be in range 5 to 35")
        }
        require(workers.map { it.name }.distinct().size == workers.size) {
            throw IllegalArgumentException("worker's name must be distinct")
        }
    }

    companion object {
        fun of(workerNames: List<String>): Workers {
            val workers = ArrayDeque(workerNames.map { Worker(Name(it)) })
            return Workers(workers)
        }
    }
}
