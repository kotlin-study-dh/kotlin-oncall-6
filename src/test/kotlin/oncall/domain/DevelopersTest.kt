package oncall.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DevelopersTest {

    @ParameterizedTest
    @ValueSource(ints = [4, 36])
    fun `should throw exception when number of developers is out of range`(numberOfDevelopers: Int) {
        // Given
        val developers = (1..numberOfDevelopers).map { Developer("dev$it") }

        // When & Then
        assertThrows<IllegalArgumentException>{
            Developers(developers)
        }
    }

    @Test
    fun `should have unique developer names`() {
        // Given
        val developers = (1..5).map { Developer("dev$it") } + Developer("dev1")

        // When & Then
        assertThrows<IllegalArgumentException> {
            Developers(developers)
        }
    }

}