package oncall.domain

import oncall.domain.DayOfWeekType.WEEKDAY
import oncall.domain.DayOfWeekType.WEEKEND

enum class DayOfWeek(val abbreviation: String, val type: DayOfWeekType) {
    MONDAY("Mon", WEEKDAY),
    TUESDAY("Tue", WEEKDAY),
    WEDNESDAY("Wed", WEEKDAY),
    THURSDAY("Thu", WEEKDAY),
    FRIDAY("Fri", WEEKDAY),
    SATURDAY("Sat", WEEKEND),
    SUNDAY("Sun", WEEKEND);

    fun tomorrow(): DayOfWeek = when (this) {
        MONDAY -> TUESDAY
        TUESDAY -> WEDNESDAY
        WEDNESDAY -> THURSDAY
        THURSDAY -> FRIDAY
        FRIDAY -> SATURDAY
        SATURDAY -> SUNDAY
        SUNDAY -> MONDAY
    }

    companion object {
        private val abbreviationToDay: Map<String, DayOfWeek> = entries.associateBy { it.abbreviation }
        fun from(abbreviation: String): DayOfWeek =
            abbreviationToDay[abbreviation] ?: throw IllegalArgumentException("Invalid day abbreviation: $abbreviation")
    }
}

enum class DayOfWeekType {
    WEEKDAY,
    WEEKEND;
}