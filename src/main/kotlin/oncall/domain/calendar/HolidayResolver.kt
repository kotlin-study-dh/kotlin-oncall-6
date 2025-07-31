package oncall.domain.calendar

class HolidayResolver {

    fun resolve(day: Day): Boolean {
        return HOLIDAY.contains(Day(day.month, day.day))
    }

    companion object {
        private val HOLIDAY = listOf(
            Day(1, 1), Day(3, 1), Day(5, 5),
            Day(6, 6), Day(8, 15), Day(10, 3),
            Day(10, 9), Day(12, 25)
        )
    }
}