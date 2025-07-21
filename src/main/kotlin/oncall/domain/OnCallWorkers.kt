package oncall.domain

data class OnCallWorkers(
    val weekdayWorkers: Workers,
    val holidayWorkers: Workers
) {

    init {
        require(
            holidayWorkers.workers.map { it.name }.toSet()
                    == weekdayWorkers.workers.map { it.name }.toSet()
        ) {
            throw IllegalArgumentException("Weekday and holiday oncall workers must be same")
        }
    }
}
