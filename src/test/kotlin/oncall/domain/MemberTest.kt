package oncall.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MemberTest {

    @Test
    fun `length of member name must be less equal then 35`() {
        assertThrows<IllegalArgumentException> { Member(name = "a".repeat(6)) }
    }
}