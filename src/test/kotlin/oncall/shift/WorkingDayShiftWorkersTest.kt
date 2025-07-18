package oncall.shift

import oncall.shift.ShiftWorkers.WorkingDayShiftWorkers
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class WorkingDayShiftWorkersTest {
    @Test
    fun `should fail to create WorkingDayShiftWorkers when worker names are not unique`() {
        // given
        val workerNames = listOf("Capy", "Capy", "Rosie")

        // when & then
        assertThatThrownBy { WorkingDayShiftWorkers.from(workerNames) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Shift worker names must be unique. WorkingDayShiftWorkers: $workerNames")
    }

    @Test
    fun `should fail to create WorkingDayShiftWorkers when there are less than 2 workers`() {
        // given
        val workerNames = listOf("Rosie")

        // when & then
        assertThatThrownBy { WorkingDayShiftWorkers.from(workerNames) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("There must be at least 2 shift workers. WorkingDayShiftWorkers: $workerNames")
    }
}
