package oncall.shift

import oncall.shift.ShiftWorkers.WorkingDayShiftWorkers
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class ShiftWorkersTest {
    @Test
    fun `should fail to create ShiftWorkers when worker names are not unique`() {
        // given
        val workerNames = listOf("Capy", "Capy", "Rosie")

        // when & then
        assertThatThrownBy { WorkingDayShiftWorkers.from(workerNames) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Shift worker names must be unique. WorkingDayShiftWorkers: $workerNames")
    }

    @Test
    fun `should fail to create ShiftWorkers when there are less than 2 workers`() {
        // given
        val workerNames = listOf("Rosie")

        // when & then
        assertThatThrownBy { WorkingDayShiftWorkers.from(workerNames) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("There must be at least 2 shift workers. WorkingDayShiftWorkers: $workerNames")
    }

    @Test
    fun `should return a first worker and offer it back`() {
        // given
        val workerNames = listOf("Capy", "Rosie")
        val workingDayShiftWorkers = WorkingDayShiftWorkers.from(workerNames)

        // when & then
        SoftAssertions.assertSoftly { softly ->
            softly.assertThat(workingDayShiftWorkers.getWorker().name).isEqualTo("Capy")
            softly.assertThat(workingDayShiftWorkers.getWorker().name).isEqualTo("Rosie")
            softly.assertThat(workingDayShiftWorkers.getWorker().name).isEqualTo("Capy")
            softly.assertThat(workingDayShiftWorkers.getWorker().name).isEqualTo("Rosie")
        }
    }
}
