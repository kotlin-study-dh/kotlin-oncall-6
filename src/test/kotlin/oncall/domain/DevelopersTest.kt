package oncall.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DevelopersTest {
    @Test
    fun `throws exception when duplicate developer names`() {
        // Given
        val namesWithDuplicates = listOf(Developer("A"), Developer("B"), Developer("A"), Developer("C"), Developer("D"))

        // When & Then
        assertThrows<IllegalArgumentException> {
            Developers(namesWithDuplicates)
        }
    }

    @Test
    fun `throws exception when less than 5 developers`() {
        // Given
        val lessThanMinimum = listOf(Developer("A"), Developer("B"), Developer("C"), Developer("D"))

        // When & Then
        assertThrows<IllegalArgumentException> {
            Developers(lessThanMinimum)
        }
    }

    @Test
    fun `throws exception when more than 35 developers`() {
        // Given
        val moreThanMaximum = (1..36).map { Developer(it.toString()) }

        // When & Then
        assertThrows<IllegalArgumentException> {
            Developers(moreThanMaximum)
        }
    }

    @Test
    fun `next returns developers in circular order`() {
        // Given
        val developers = Developers(
            (1..5).map { Developer(it.toString()) }
        )

        // When
        val result = (1..7).map { developers.next() }

        // Then
        assertThat(result.map { it.name }).isEqualTo(listOf("1", "2", "3", "4", "5", "1", "2"))
    }

    @Test
    fun `rearrange returns new instance with swapped order`() {
        // Given
        val developers = Developers(
            listOf(Developer("A"), Developer("B"), Developer("C"), Developer("D"), Developer("E"))
        )

        // When
        val rearranged = developers.rearrange(Developer("C"))

        // Then
        val rearrangedOrder = (1..5).map { rearranged.next().name }
        assertThat(rearrangedOrder).isEqualTo(listOf("A", "B", "D", "C", "E"))
    }
}
