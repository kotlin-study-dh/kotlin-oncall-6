package oncall.domain.service

import oncall.domain.calendar.Calendar
import oncall.domain.member.Members
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.DayOfWeek

class SchedulerTest {

    @Test
    fun `holiday and weekday member list must be equal`() {
        // given
        val calendar: Calendar = Calendar(1, DayOfWeek.MONDAY)
        val weekday = Members(listOf("a", "b", "c", "d", "e"))
        val weekend = Members(listOf("c", "b", "a", "e", "d"))

        // when & then
        assertThatCode { Scheduler(calendar, weekday, weekend) }
            .doesNotThrowAnyException()
    }

    @Test
    fun `throw an exception when weekday and weekend members are different`() {
        val calendar: Calendar = Calendar(1, DayOfWeek.MONDAY)
        val weekday = Members(listOf("a", "b", "c", "d", "z"))
        val weekend = Members(listOf("c", "b", "a", "e", "d"))

        // when & then
        assertThrows<IllegalArgumentException> { Scheduler(calendar, weekday, weekend) }
    }
}