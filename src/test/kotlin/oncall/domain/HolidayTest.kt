package oncall.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class HolidayTest {
    @Test
    fun `isHoliday returns true for all defined holidays`() {
        Holiday.entries.forEach {
            assertThat(Holiday.isHoliday(it.month, it.day)).isTrue()
        }
    }

    @Test
    fun `isHoliday returns false for non-holiday dates`() {
        assertThat(Holiday.isHoliday(Month.JANUARY, 2)).isFalse()
        assertThat(Holiday.isHoliday(Month.FEBRUARY, 1)).isFalse()
        assertThat(Holiday.isHoliday(Month.DECEMBER, 24)).isFalse()
    }
} 