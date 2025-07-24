package oncall.domain

class OnCallOrder(
    val weekdayOrder: Developers,
    val holidayOrder: Developers
) {
    init {
        require(weekdayOrder.hasSameDevelopers(holidayOrder)) {
            "Developers must be the same in both orders."
        }
    }
}