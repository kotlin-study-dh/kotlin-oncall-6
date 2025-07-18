package oncall.shift

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class ShiftWorkersTest {
    @Test
    fun `should fail to create ShiftWorkers when worker names are not unique`() {
        // given
        val workerNames = listOf("Capy", "Rosie", "Prin", "Prin", "Pram")

        // when & then
        assertThatThrownBy { ShiftWorkers.from(workerNames) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Shift worker names must be unique.")
    }

    @Test
    fun `should fail to create ShiftWorkers when there are less than 5 workers`() {
        // given
        val workerNames = listOf("Capy", "Rosie", "Prin", "Pram")

        // when & then
        assertThatThrownBy { ShiftWorkers.from(workerNames) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("There must be at least 5 shift workers. Current shift worker count: ${workerNames.size}")
    }

    @Test
    fun `should succeed in creating ShiftWorkers when worker names are unique and there are at least 5 workers`() {
        // given
        val workerNames = listOf("Capy", "Rosie", "Prin", "Pram", "Tre")

        // when
        val shiftWorkers = ShiftWorkers.from(workerNames)

        // then
        assertThat(shiftWorkers.shiftWorkers.map { it.name }).containsExactlyInAnyOrderElementsOf(workerNames)
    }
}
