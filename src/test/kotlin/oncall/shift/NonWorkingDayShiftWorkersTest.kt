package oncall.shift

import oncall.shift.ShiftWorkers.NonWorkingDayShiftWorkers
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class NonWorkingDayShiftWorkersTest {
    @Test
    fun `should fail to create NonWorkingDayShiftWorkers when worker names are not unique`() {
        // given
        val workerNames = listOf("Capy", "Capy", "Rosie")

        // when & then
        assertThatThrownBy { NonWorkingDayShiftWorkers.from(workerNames) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Shift worker names must be unique. NonWorkingDayShiftWorkers: $workerNames")
    }

    @Test
    fun `should fail to create NonWorkingDayShiftWorkers when there are less than 2 workers`() {
        // given
        val workerNames = listOf("Rosie")

        // when & then
        assertThatThrownBy { NonWorkingDayShiftWorkers.from(workerNames) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("There must be at least 2 shift workers. NonWorkingDayShiftWorkers: $workerNames")
    }
}
