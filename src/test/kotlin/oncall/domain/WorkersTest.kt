package oncall.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WorkersTest {

    @Test
    fun `Workers creation succeeds with 5 to 35 workers`() {
        val workerNames = listOf("a", "b", "c", "d", "e") // 5명
        val workers = Workers.of(workerNames)
        assertThat(workers.workers.size).isEqualTo(5)
    }

    @Test
    fun `Workers creation throws IllegalArgumentException for less than 5 workers`() {
        val workerNames = listOf("a", "b", "c", "d")
        assertThat(
            assertThrows<IllegalArgumentException> {
                Workers.of(workerNames)
            }
        ).hasMessage("workers must be in range 5 to 35")
    }

    @Test
    fun `Workers creation throws IllegalArgumentException for more than 35 workers`() {
        val workerNames = (1..36).map { "$it" }
        val exception = assertThrows<IllegalArgumentException> {
            Workers.of(workerNames)
        }
        assertThat(exception.message).isEqualTo("workers must be in range 5 to 35")
    }

    @Test
    fun `Workers creation throws IllegalArgumentException for duplicate worker names`() {
        val workerNames = listOf("a", "a", "b", "c", "d")
        assertThat(
            assertThrows<IllegalArgumentException> {
                Workers.of(workerNames)
            }
        ).hasMessage("worker's name must be distinct")
    }

    @Test
    fun `getAssignedWorkerForDay assigns next worker if different from last`() {
        val workers = Workers.of(listOf("a", "b", "c", "d", "e"))
        val lastAssignedWorker = Worker(Name("a"))

        val assignedWorker = workers.getAssignedWorkerForDay(lastAssignedWorker)

        assertThat(assignedWorker.name.value).isEqualTo("b")
        assertThat(workers.workers.map { it.name.value }).containsExactly("a", "c", "d", "e", "a", "b")


    }

    @Test
    fun `getNextWorkerAndRotate assigns next worker and rotates`() {
        val workers = Workers.of(listOf("a", "b", "c", "d", "e"))

        val nextWorker = workers.getNextWorkerAndRotate()

        assertThat(nextWorker.name.value).isEqualTo("a")
        assertThat(workers.workers.map { it.name.value }).containsExactly("b", "c", "d", "e", "a")
    }
}