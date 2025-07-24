package oncall.domain

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DeveloperTest {

    @ParameterizedTest
    @ValueSource(strings = ["heeeeey", "Kotlin", "", "  ", "\t"])
    fun `should throw exception when the name length is invalid`(invalidName: String) {
        // When & Then
        assertThrows(IllegalArgumentException::class.java) {
            Developer(invalidName)
        }
    }

}