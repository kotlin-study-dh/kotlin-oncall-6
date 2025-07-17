package oncall.worker

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class WorkersTest {
    @Test
    fun `should fail to create Workers when worker names are not unique`() {
        // given
        val workerNames = listOf("Capy", "Rosie", "Prin", "Prin", "Pram")

        // when & then
        assertThatThrownBy { Workers.from(workerNames) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Worker names must be unique.")
    }

    @Test
    fun `should fail to create Workers when there are less than 5 workers`() {
        // given
        val workerNames = listOf("Capy", "Rosie", "Prin", "Pram")

        // when & then
        assertThatThrownBy { Workers.from(workerNames) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("There must be at least 5 workers. Current worker count: ${workerNames.size}")
    }

    @Test
    fun `should succeed in creating Workers when worker names are unique and there are at least 5 workers`() {
        // given
        val workerNames = listOf("Capy", "Rosie", "Prin", "Pram", "Tre")

        // when
        val workers = Workers.from(workerNames)

        // then
        assertThat(workers.workers.map { it.name }).containsExactlyInAnyOrderElementsOf(workerNames)
    }
}
