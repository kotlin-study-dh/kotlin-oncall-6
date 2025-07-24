package oncall

import oncall.domain.DayOfWeek
import oncall.domain.Developer
import oncall.domain.Developers
import oncall.domain.Month
import oncall.domain.OnCallOrder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OnCallServiceTest {

    @Test
    fun `no consecutive assignment for any developer`() {
        val weekdayDevs = Developers(listOf("A", "B", "C", "D", "E").map { Developer(it) })
        val holidayDevs = Developers(listOf("A", "B", "C", "D", "E").map { Developer(it) })
        val service = OnCallService()
        val month = Month.JANUARY
        val startDay = DayOfWeek.FRIDAY
        val result = service.schedule(month, startDay, OnCallOrder(weekdayDevs, holidayDevs))
        for (i in 1 until result.size) {
            assertThat(result[i].developer).isNotEqualTo(result[i - 1].developer)
        }
    }

    @Test
    fun `rearrange is triggered and order is changed when consecutive assignment would occur`() {
        val weekdayDevs = Developers(listOf("A", "B", "C", "D", "E").map { Developer(it) })
        val holidayDevs = Developers(listOf("A", "B", "C", "D", "E").map { Developer(it) })
        val service = OnCallService()
        val month = Month.JANUARY
        val startDay = DayOfWeek.FRIDAY
        val result = service.schedule(month, startDay, OnCallOrder(weekdayDevs, holidayDevs))
        for (i in 1 until result.size) {
            assertThat(result[i].developer).isNotEqualTo(result[i - 1].developer)
        }
    }
}