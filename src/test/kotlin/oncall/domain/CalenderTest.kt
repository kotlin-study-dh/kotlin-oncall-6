package oncall.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.DayOfWeek
import java.time.Month

class CalenderTest {

    @Test
    fun `static factory method generate the Calender in monthly correct`() {
        val januaryCalendar = Calender.of(Month.JANUARY, DayOfWeek.MONDAY)
        val februaryCalendar = Calender.of(Month.FEBRUARY, DayOfWeek.WEDNESDAY)
        val aprilCalendar = Calender.of(Month.APRIL, DayOfWeek.SATURDAY)
        val decemberCalendar = Calender.of(Month.DECEMBER, DayOfWeek.FRIDAY)

        assertEquals(31, januaryCalendar.totalDaysInMonth)
        assertEquals(28, februaryCalendar.totalDaysInMonth)
        assertEquals(30, aprilCalendar.totalDaysInMonth)
        assertEquals(31, decemberCalendar.totalDaysInMonth)
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "1, FRIDAY",
            "2, SATURDAY",
            "3, SUNDAY",
            "4, MONDAY",
            "7, THURSDAY",
            "8, FRIDAY",
            "31, SUNDAY"
        ]
    )
    fun `calculateDayOfWeek calculates day of week correctly`(date: Int, expectedDayOfWeekName: String) {
        val decemberCalendar = Calender.of(Month.DECEMBER, DayOfWeek.FRIDAY)
        val expectedDayOfWeek = DayOfWeek.valueOf(expectedDayOfWeekName)

        val actualDayOfWeek = decemberCalendar.calculateDayOfWeek(date)

        assertEquals(expectedDayOfWeek, actualDayOfWeek, "Day of start month $date is not valid")
    }

    @ParameterizedTest
    @CsvSource(value = ["0", "32"]) // 12월은 31일까지
    fun `calculateDayOfWeek throws exception for invalid date`(invalidDate: Int) {
        val decemberCalendar = Calender.of(Month.DECEMBER, DayOfWeek.FRIDAY)
        val exception = assertThrows<IllegalArgumentException> {
            decemberCalendar.calculateDayOfWeek(invalidDate)
        }

        assertEquals("Invalid date $invalidDate", exception.message)
    }

    @Test
    fun `calculateWeekend calculates all weekend days in month correctly`() {
        val decemberCalendar = Calender.of(Month.DECEMBER, DayOfWeek.MONDAY)
        val expectedWeekends = setOf(6, 7, 13, 14, 20, 21, 27, 28)

        val actualWeekends = decemberCalendar.calculateWeekend()

        assertEquals(expectedWeekends, actualWeekends)
    }
}
