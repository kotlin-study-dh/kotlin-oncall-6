package oncall.worker

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WorkerTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 6])
    fun `should fail to create Worker when name size is less than 2 or greater than 5`(invalidNameSize: Int) {
        // given
        val name = "a".repeat(invalidNameSize)

        // when & then
        assertThatThrownBy {
            Worker(name)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Name must be between 2 and 5 characters. invalid name: $name")
    }

    @ParameterizedTest
    @ValueSource(ints = [2, 5])
    fun `should succeed in creating Worker when name size is between 2 and 5`(validNameSize: Int) {
        // given
        val name = "a".repeat(validNameSize)

        // when
        val worker = Worker(name)

        // then
        assertThat(worker.name).isEqualTo(name)
    }
}
