package oncall.domain.calendar

import org.junit.jupiter.api.Test
import java.time.DayOfWeek

class CalendarTest {

    @Test
    fun `retrieve weekdays`() {
        // given
        val calendar = Calendar(7, DayOfWeek.TUESDAY)

        // when & then
        assert(calendar.retrieveWeekdays().size == 23)
    }

    @Test
    fun `retrieve weekends`() {
        // given
        val calendar = Calendar(7, DayOfWeek.TUESDAY)

        // when & then
        assert(calendar.retrieveWeekends().size == 8)
    }


}