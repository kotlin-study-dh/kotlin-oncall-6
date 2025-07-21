package oncall

import oncall.domain.Calender
import oncall.domain.Name
import oncall.domain.OnCallWorkers
import oncall.domain.Worker
import oncall.domain.Workers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.DayOfWeek
import java.time.Month

class NonConsecutiveRotationStrategyTest {

    @ParameterizedTest
    @CsvSource(
        value = [
            "1, a, 월, false", "2, b, 화, false", "3, c, 수, false", "4, d, 목, false ", "5, e, 금, false",
            "6, b, 토, false", "7, c, 일, false", "8, a, 월, false", "9, b, 화, false ", "10, c, 수, false",
            "11, d, 목, false", "12, e, 금, false", "13, d, 토, false", "14, e, 일, false", "15, a, 월, false",
            "16, b, 화, false", "17, c, 수, false", "18, d, 목, false", "19, e, 금, false", "20, a, 토, false",
            "21, b, 일, false", "22, a, 월, false", "23, b, 화, false", "24, c, 수, false", "25, d, 목, false",
            "26, e, 금, false", "27, c, 토, true", "28, e, 일, false", "29, d, 월, true", "30, a, 화, false",
            "31, b, 수, false"
        ]
    )
    fun `assign workers`(date: Int, expectedWorker: String, day: String, isTemporary: Boolean) {
        val onCallWorkers = OnCallWorkers(
            Workers.of(listOf("a", "b", "c", "d", "e")),
            Workers.of(listOf("b", "c", "d", "e", "a"))
        )
        val calender = Calender(Month.DECEMBER, 31, DayOfWeek.MONDAY)
        val strategy = NonConsecutiveRotationStrategy()

        val assignment = strategy.assign(onCallWorkers, calender)[date - 1]

        assertThat(assignment).isEqualTo(
            DailyOnCallAssignment(
                Worker(Name(expectedWorker), isTemporary),
                OnCallDate(Month.DECEMBER, date, toDayOfWeek(day))
            )
        )
    }
}
