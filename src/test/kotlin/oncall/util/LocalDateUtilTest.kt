package oncall.util

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import java.time.DayOfWeek
import java.time.LocalDate

class LocalDateUtilTest {
    @ParameterizedTest(name = "dayOfMonth={0}")
    @ValueSource(ints = [1, 3])
    fun `should return false when the date is a weekend or special day`(dayOfMonth: Int) {
        // given
        val localDate = LocalDate.of(2023, 10, dayOfMonth)

        // when
        val isWorkingDay = localDate.isWorkingDay()

        // then
        assertThat(isWorkingDay).isFalse()
    }

    @Test
    fun `should return true when the date is a weekday and not public holiday`() {
        // given
        val localDate = LocalDate.of(2023, 10, 2)

        // when
        val isWorkingDay = localDate.isWorkingDay()

        // then
        assertThat(isWorkingDay).isTrue()
    }

    @Test
    fun `should return false when the date is a weekday`() {
        // given
        val localDate = LocalDate.of(2023, 10, 2)

        // when
        val isWorkingDay = localDate.isNonWorkingDay()

        // then
        assertThat(isWorkingDay).isFalse()
    }

    @ParameterizedTest(name = "dayOfMonth={0}")
    @ValueSource(ints = [1, 3])
    fun `should return true when the date is a weekend or public holiday`() {
        // given
        val localDate = LocalDate.of(2023, 10, 3)

        // when
        val isNonWorkingDay = localDate.isNonWorkingDay()

        // then
        assertThat(isNonWorkingDay).isTrue()
    }

    @Test
    fun `should return false when the date is not a public holiday`() {
        // given
        val localDate = LocalDate.of(2023, 10, 2)

        // when
        val isPublicHoliday = localDate.isPublicHoliday()

        // then
        assertThat(isPublicHoliday).isFalse()
    }

    @Test
    fun `should return true when the date is a public holiday`() {
        // given
        val localDate = LocalDate.of(2023, 10, 3)

        // when
        val isPublicHoliday = localDate.isPublicHoliday()

        // then
        assertThat(isPublicHoliday).isTrue()
    }

    @ParameterizedTest
    @CsvSource(
        "2023-10-02, 월",
        "2023-10-03, 화",
        "2023-10-04, 수",
        "2023-10-05, 목",
        "2023-10-06, 금",
        "2023-10-07, 토",
        "2023-10-08, 일"
    )
    fun `should return the correct Korean day of the week`(date: String, expectedKorDayOfWeek: String) {
        // given
        val localDate = LocalDate.parse(date)

        // when
        val korDayOfWeek = localDate.korDayOfWeek()

        // then
        assertThat(korDayOfWeek).isEqualTo(expectedKorDayOfWeek)
    }

    @Test
    fun `should return the previous day correctly`() {
        // given
        val localDate = LocalDate.of(2023, 10, 2)

        // when
        val previousDate = localDate.previous()

        // then
        assertThat(previousDate).isEqualTo(LocalDate.of(2023, 10, 1))
    }

    @Test
    fun `should return the next day correctly`() {
        // given
        val localDate = LocalDate.of(2023, 10, 2)

        // when
        val nextDate = localDate.next()

        // then
        assertThat(nextDate).isEqualTo(LocalDate.of(2023, 10, 3))
    }

    @Test
    fun `should return false when the month is different`() {
        // given
        val localDate = LocalDate.of(2023, 10, 31)
        val otherDate = LocalDate.of(2023, 11, 1)

        // when
        val isSameMonth = localDate.isSameMonth(otherDate)

        // then
        assertThat(isSameMonth).isFalse()
    }

    @Test
    fun `should return true when the month is the same`() {
        // given
        val localDate = LocalDate.of(2023, 10, 30)
        val otherDate = LocalDate.of(2023, 10, 31)

        // when
        val isSameMonth = localDate.isSameMonth(otherDate)

        // then
        assertThat(isSameMonth).isTrue()
    }

    @ParameterizedTest(name = "invalidMonth={0}")
    @ValueSource(ints = [0, 13])
    fun `should fail to resolve first matching day of week for invalid month`(invalidMonth: Int) {
        // when & then
        assertThatThrownBy {
            LocalDateUtil.resolveFirstMatchingDayOfWeek(invalidMonth, DayOfWeek.MONDAY)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Month must be between 1 and 12. Invalid month: $invalidMonth.")
    }

    @ParameterizedTest
    @CsvSource(
        "10, TUESDAY, 2023-10-03",
        "11, MONDAY, 2023-11-06",
        "12, FRIDAY, 2023-12-01",
    )
    fun `should resolve the first matching day of the week for a given month`(
        month: Int,
        targetDayOfWeek: DayOfWeek,
        expectedDate: String,
    ) {
        // when
        val firstMatchingDate = LocalDateUtil.resolveFirstMatchingDayOfWeek(month, targetDayOfWeek)

        // then
        assertThat(firstMatchingDate).isEqualTo(LocalDate.parse(expectedDate))
    }

    @Test
    fun `should fail to get day of week from invalid Korean day of week`() {
        // given
        val invalidKorDayOfWeek = "월월"

        // when & then
        assertThatThrownBy { LocalDateUtil.getDayOfWeekFromKor(invalidKorDayOfWeek) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Invalid Korean day of week: $invalidKorDayOfWeek")
    }

    @ParameterizedTest
    @CsvSource(
        "월, MONDAY",
        "화, TUESDAY",
        "수, WEDNESDAY",
        "목, THURSDAY",
        "금, FRIDAY",
        "토, SATURDAY",
        "일, SUNDAY"
    )
    fun `should succeed in getting day of week from valid Korean day of week`(
        korDayOfWeek: String,
        expectedDayOfWeek: DayOfWeek,
    ) {
        // when
        val dayOfWeek = LocalDateUtil.getDayOfWeekFromKor(korDayOfWeek)

        // then
        assertThat(dayOfWeek).isEqualTo(expectedDayOfWeek)
    }
}
