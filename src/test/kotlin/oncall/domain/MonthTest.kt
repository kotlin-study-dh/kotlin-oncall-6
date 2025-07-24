package oncall.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MonthTest {
    @Test
    fun `from returns correct Month for valid values`() {
        Month.entries.forEach {
            assertThat(Month.from(it.value)).isEqualTo(it)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 13, -1, 100])
    fun `from throws exception for invalid value`(invalid: Int) {
        assertThrows<IllegalArgumentException> {
            Month.from(invalid)
        }
    }
} 