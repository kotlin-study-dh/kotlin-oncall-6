package oncall.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OnCallOrderTest {

    @ParameterizedTest
    @ValueSource(ints = [4, 36])
    fun `should throw exception when number of developers is out of range`(numberOfDevelopers: Int) {
        // Given
        val developers = (1..numberOfDevelopers).map { Developer("dev$it") }

        // When & Then
        assertThrows<IllegalArgumentException>{
            OnCallOrder(developers)
        }
    }

    @Test
    fun `should have unique developer names`() {
        // Given
        val developers = (1..5).map { Developer("dev$it") } + Developer("dev1")

        // When & Then
        assertThrows<IllegalArgumentException> {
            OnCallOrder(developers)
        }
    }

    @Test
    fun `swap developer`() {
        // Given
        val developers = listOf<Developer>(Developer("dev1"), Developer("dev2"), Developer("dev3"))

        // When
        val swapped = developers.swap(1, 2)

        // Then
        assertThat(swapped[1]).isEqualTo(Developer("dev3"))
        assertThat(swapped[2]).isEqualTo(Developer("dev2"))
    }

    @Test
    fun `rearrange developer given as an argument`() {
        // Given
        val developers = listOf(
            Developer("dev1"),
            Developer("dev2"),
            Developer("dev3"),
            Developer("dev4"),
            Developer("dev5")
        )
        val onCallOrder = OnCallOrder(developers)

        // When
        val rearranged = onCallOrder.rearrange(Developer("dev5"))

        // Then
        assertThat(rearranged.developers[0]).isEqualTo(Developer("dev5"))
        assertThat(rearranged.developers[4]).isEqualTo(Developer("dev1"))
    }

}