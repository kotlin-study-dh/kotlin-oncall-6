package oncall.domain.calendar

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class HolidayResolverTest {

    @ParameterizedTest
    @CsvSource("1, 1", "3, 1", "5, 5", "6, 6", "8, 15", "10, 3", "10, 9", "12, 25")
    fun `resolve legal holiday`(month: Int, day: Int) {
        // given
        val holidayResolver = HolidayResolver()

        // when & then
        assert(holidayResolver.resolve(Day(month, day)))
    }
}