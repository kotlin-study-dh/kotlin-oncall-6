package oncall.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class OnCallWorkersTest {

    @Test
    fun `validate weekend and holiday worker have same worker name`() {
        val weekdayNames = listOf("a", "b", "c", "d", "e")
        val holidayNames = listOf("a", "b", "c", "d", "f")

        val weekdayWorkers = Workers.of(weekdayNames)
        val holidayWorkers = Workers.of(holidayNames)

        assertThat(
            assertThrows<IllegalArgumentException> {
                OnCallWorkers(weekdayWorkers, holidayWorkers)
            }
        ).hasMessage("Weekday and holiday oncall workers must be same")

    }
}