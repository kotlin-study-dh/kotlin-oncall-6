package oncall

import java.time.DayOfWeek

fun toStringDayOfWeek(dayOfWeek: DayOfWeek): String {
    return when (dayOfWeek) {
        DayOfWeek.MONDAY -> "월"
        DayOfWeek.TUESDAY -> "화"
        DayOfWeek.WEDNESDAY -> "수"
        DayOfWeek.THURSDAY -> "목"
        DayOfWeek.FRIDAY -> "금"
        DayOfWeek.SATURDAY -> "토"
        DayOfWeek.SUNDAY -> "일"
    }
}

fun toDayOfWeek(stringDayOfWeek: String): DayOfWeek {
    return when (stringDayOfWeek) {
        "월" -> DayOfWeek.MONDAY
        "화" -> DayOfWeek.TUESDAY
        "수" -> DayOfWeek.WEDNESDAY
        "목" -> DayOfWeek.THURSDAY
        "금" -> DayOfWeek.FRIDAY
        "토" -> DayOfWeek.SATURDAY
        "일" -> DayOfWeek.SUNDAY
        else -> throw IllegalArgumentException("$stringDayOfWeek is not a valid day of week")
    }
}
