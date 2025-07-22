package oncall.domain.calendar

import java.time.DayOfWeek

enum class CustomDayOfWeek(val text: String, val dayOfWeek: DayOfWeek) {
    MONDAY("월", DayOfWeek.MONDAY),
    TUESDAY("화", DayOfWeek.TUESDAY),
    WEDNESDAY("수", DayOfWeek.WEDNESDAY),
    THURSDAY("목", DayOfWeek.THURSDAY),
    FRIDAY("금", DayOfWeek.FRIDAY),
    SATURDAY("토", DayOfWeek.SATURDAY),
    SUNDAY("일", DayOfWeek.SUNDAY);

    companion object {
        fun from(korean: String): CustomDayOfWeek {
            return CustomDayOfWeek.entries.first { korean == it.text }
        }

        fun from(dayOfWeek: DayOfWeek): CustomDayOfWeek {
            return CustomDayOfWeek.entries.first { it.dayOfWeek == dayOfWeek }
        }
    }
}