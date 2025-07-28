package oncall.domain.member

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.system.measureNanoTime

class MemberTest {

    @Test
    fun `length of member name must be less equal then 5`() {
        assertThrows<IllegalArgumentException> { Member(name = "a".repeat(6)) }
    }

    @Test
    fun `length of member name must not be blank`() {
        assertThrows<IllegalArgumentException> { Member(name = " ") }
    }

    @Test
    fun benchmark_with_special_char_check() {
        val time = measureNanoTime {
            validNames.forEach { name ->
                MemberWithSpecialCheck(name)
            }
        }
        println("With special character check: ${time / 1_000_000} ms")
    }

    @Test
    fun benchmark_without_special_char_check() {
        val time = measureNanoTime {
            validNames.forEach { name ->
                MemberWithNormalCheck(name)
            }
        }
        println("With special character check: ${time / 1_000_000} ms")
    }

    data class MemberWithSpecialCheck(val name: String) {
        init {
            require(name.length <= 5) { "Too long" }
            require(name.isNotBlank()) { "Blank" }
            require(!regex.containsMatchIn(name)) {
                "Contains special character"
            }
        }
    }

    data class MemberWithNormalCheck(val name: String) {
        init {
            require(name.length <= 5) { "Too long" }
            require(name.isNotBlank()) { "Blank" }
        }
    }

    companion object {
        private const val ITERATIONS = 1_000_000_0
        private val regex = Regex("[^a-zA-Z0-9가-힣\\s]")
        private val validNames = List(ITERATIONS) { "홍길동" }
        private val invalidNames = List(ITERATIONS) { "홍@길#동!" }
    }
}