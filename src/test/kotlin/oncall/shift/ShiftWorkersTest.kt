package oncall.shift

import oncall.shift.ShiftWorkers.WorkingDayShiftWorkers
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class ShiftWorkersTest {
    @Test
    fun `should fail to create ShiftWorkers when worker names are not unique`() {
        // given
        val workerNames = listOf("Capy", "Capy", "Rosie", "Pram", "Tre")

        // when & then
        assertThatThrownBy { WorkingDayShiftWorkers.from(workerNames) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Shift worker names must be unique. WorkingDayShiftWorkers: $workerNames")
    }

    @Test
    fun `should fail to create ShiftWorkers when there are less than 5 workers`() {
        // given
        val workerNames = listOf("Rosie", "Pram", "Tre", "Kirby")

        // when & then
        assertThatThrownBy { WorkingDayShiftWorkers.from(workerNames) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Unique shift workers must be between 5 and 35. Invalid WorkingDayShiftWorkers size: ${workerNames.size}.")
    }

    @Test
    fun `should fail to create ShiftWorkers when there are greater than 35 workers`() {
        // given
        val workerNames = listOf(
            "aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh", "ii", "jj",
            "kk", "ll", "mm", "nn", "oo", "pp", "qq", "rr", "ss", "tt",
            "uu", "vv", "ww", "xx", "yy", "zz", "aaa", "bbb", "ccc", "ddd",
            "eee", "fff", "ggg", "hhh", "iii", "jjj",
        )

        // when & then
        assertThatThrownBy { WorkingDayShiftWorkers.from(workerNames) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Unique shift workers must be between 5 and 35. Invalid WorkingDayShiftWorkers size: ${workerNames.size}.")
    }

    @Test
    fun `should return a first worker and offer it back`() {
        // given
        val workerNames = listOf("Capy", "Rosie", "Pram", "Tre", "Kirby")
        val workingDayShiftWorkers = WorkingDayShiftWorkers.from(workerNames)

        // when & then
        SoftAssertions.assertSoftly { softly ->
            softly.assertThat(workingDayShiftWorkers.getWorker().name).isEqualTo("Capy")
            softly.assertThat(workingDayShiftWorkers.getWorker().name).isEqualTo("Rosie")
            softly.assertThat(workingDayShiftWorkers.getWorker().name).isEqualTo("Pram")
            softly.assertThat(workingDayShiftWorkers.getWorker().name).isEqualTo("Tre")
            softly.assertThat(workingDayShiftWorkers.getWorker().name).isEqualTo("Kirby")
            softly.assertThat(workingDayShiftWorkers.getWorker().name).isEqualTo("Capy")
            softly.assertThat(workingDayShiftWorkers.getWorker().name).isEqualTo("Rosie")
            softly.assertThat(workingDayShiftWorkers.getWorker().name).isEqualTo("Pram")
            softly.assertThat(workingDayShiftWorkers.getWorker().name).isEqualTo("Tre")
            softly.assertThat(workingDayShiftWorkers.getWorker().name).isEqualTo("Kirby")
        }
    }
}
