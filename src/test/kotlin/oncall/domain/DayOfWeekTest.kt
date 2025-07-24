package oncall.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

class DayOfWeekTest {
    companion object {
        @JvmStatic
        fun tomorrowProvider(): Stream<org.junit.jupiter.params.provider.Arguments> = Stream.of(
            org.junit.jupiter.params.provider.Arguments.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY),
            org.junit.jupiter.params.provider.Arguments.of(DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY),
            org.junit.jupiter.params.provider.Arguments.of(DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY),
            org.junit.jupiter.params.provider.Arguments.of(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY),
            org.junit.jupiter.params.provider.Arguments.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY),
            org.junit.jupiter.params.provider.Arguments.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY),
            org.junit.jupiter.params.provider.Arguments.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY)
        )
        @JvmStatic
        fun abbreviationTypeProvider(): Stream<org.junit.jupiter.params.provider.Arguments> = Stream.of(
            org.junit.jupiter.params.provider.Arguments.of(DayOfWeek.MONDAY, "Mon", DayOfWeekType.WEEKDAY),
            org.junit.jupiter.params.provider.Arguments.of(DayOfWeek.TUESDAY, "Tue", DayOfWeekType.WEEKDAY),
            org.junit.jupiter.params.provider.Arguments.of(DayOfWeek.WEDNESDAY, "Wed", DayOfWeekType.WEEKDAY),
            org.junit.jupiter.params.provider.Arguments.of(DayOfWeek.THURSDAY, "Thu", DayOfWeekType.WEEKDAY),
            org.junit.jupiter.params.provider.Arguments.of(DayOfWeek.FRIDAY, "Fri", DayOfWeekType.WEEKDAY),
            org.junit.jupiter.params.provider.Arguments.of(DayOfWeek.SATURDAY, "Sat", DayOfWeekType.WEEKEND),
            org.junit.jupiter.params.provider.Arguments.of(DayOfWeek.SUNDAY, "Sun", DayOfWeekType.WEEKEND)
        )
    }

    @ParameterizedTest
    @MethodSource("abbreviationTypeProvider")
    fun `from returns correct DayOfWeek for valid abbreviations`(day: DayOfWeek, abbreviation: String, type: DayOfWeekType) {
        assertThat(DayOfWeek.from(abbreviation)).isEqualTo(day)
    }

    @ParameterizedTest
    @ValueSource(strings = ["Xxx", ""])
    fun `from throws exception for invalid abbreviation`(invalid: String) {
        assertThrows<IllegalArgumentException> {
            DayOfWeek.from(invalid)
        }
    }

    @ParameterizedTest
    @MethodSource("tomorrowProvider")
    fun `tomorrow returns the next day in order`(today: DayOfWeek, expectedTomorrow: DayOfWeek) {
        assertThat(today.tomorrow()).isEqualTo(expectedTomorrow)
    }

    @ParameterizedTest
    @MethodSource("abbreviationTypeProvider")
    fun `each day has correct abbreviation and type`(day: DayOfWeek, abbreviation: String, type: DayOfWeekType) {
        assertThat(day.abbreviation).isEqualTo(abbreviation)
        assertThat(day.type).isEqualTo(type)
    }
} 