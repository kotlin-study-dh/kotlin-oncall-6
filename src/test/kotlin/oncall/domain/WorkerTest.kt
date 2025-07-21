package oncall.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WorkerTest {

    @Test
    fun `validate weekend and holiday worker have same worker name`() {
        assertThat(
            assertThrows<IllegalArgumentException> {
                Name("LongName")
            }
        ).hasMessage("Name must be between 1 and 5 digits")
    }
}
