package oncall.domain.member

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MembersTest {

    @Test
    fun `the number of member must greater equal than 5`() {
        assertThrows<IllegalArgumentException> {
            Members(listOf("a", "b", "c", "d"))
        }
    }

    @Test
    fun `the number of member must less equal than 35`() {
        // given
        val names = mutableListOf<String>()
        for (i in 0..35) {
            names.add(i.toString())
        }

        // when & then
        assertThrows<IllegalArgumentException> {
            Members(names)
        }
    }

    @Test
    fun `duplicate member names are not allowed`() {
        assertThrows<IllegalArgumentException> {
            Members(listOf("a", "a", "b", "c", "d"))
        }
    }
}