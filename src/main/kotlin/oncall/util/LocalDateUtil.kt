package oncall.util

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month

private const val DEFAULT_YEAR = 2023

private val publicHolidays = setOf(
    LocalDate.of(DEFAULT_YEAR, 1, 1),
    LocalDate.of(DEFAULT_YEAR, 3, 1),
    LocalDate.of(DEFAULT_YEAR, 5, 5),
    LocalDate.of(DEFAULT_YEAR, 8, 15),
    LocalDate.of(DEFAULT_YEAR, 10, 3),
    LocalDate.of(DEFAULT_YEAR, 10, 9),
    LocalDate.of(DEFAULT_YEAR, 12, 25),
)
private val dayOfWeekToKor = mapOf(
    DayOfWeek.MONDAY to "월",
    DayOfWeek.TUESDAY to "화",
    DayOfWeek.WEDNESDAY to "수",
    DayOfWeek.THURSDAY to "목",
    DayOfWeek.FRIDAY to "금",
    DayOfWeek.SATURDAY to "토",
    DayOfWeek.SUNDAY to "일",
)
private val KorToDayOfWeek = dayOfWeekToKor.entries
    .associate { (k, v) -> v to k }

fun LocalDate.isWorkingDay() = this.dayOfWeek.value in 1..5 && !this.isPublicHoliday()

fun LocalDate.isNonWorkingDay() = this.dayOfWeek.value in 6..7 || this.isPublicHoliday()

fun LocalDate.isPublicHoliday() = publicHolidays.contains(this)

fun LocalDate.korDayOfWeek() = dayOfWeekToKor[this.dayOfWeek]!!

fun LocalDate.previous() = this.minusDays(1)!!

fun LocalDate.next() = this.plusDays(1)!!

fun LocalDate.isSameMonth(other: LocalDate) = this.year == other.year && this.month == other.month

object LocalDateUtil {
    fun resolveFirstMatchingDayOfWeek(month: Int, targetDayOfWeek: DayOfWeek): LocalDate {
        require(month in Month.JANUARY.value..Month.DECEMBER.value) { "Month must be between 1 and 12. Invalid month: $month." }
        val firstDayOfMonth = LocalDate.of(DEFAULT_YEAR, month, 1)
        val dayOfWeekDiff = (targetDayOfWeek.value - firstDayOfMonth.dayOfWeek.value + 7) % 7
        return firstDayOfMonth.plusDays(dayOfWeekDiff.toLong())
    }

    fun getDayOfWeekFromKor(korDayOfWeek: String) =
        KorToDayOfWeek[korDayOfWeek] ?: throw IllegalArgumentException("Invalid Korean day of week: $korDayOfWeek")
}
