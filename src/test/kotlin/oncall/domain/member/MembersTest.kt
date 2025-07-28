package oncall.domain.member

import org.junit.jupiter.api.Assertions.assertFalse
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

    @Test
    fun `compare two member in any order`() {
        // given
        val members = Members(listOf("a", "b", "c", "d", "e"))
        val others = Members(listOf("e", "b", "c", "d", "a"))

        // when
        val result = members.compareInAnyOrder(others)

        // then
        assert(result)
    }

    @Test
    fun `compare two member in any order2`() {
        // given
        val split = "준팍,도밥,고니,수아,루루,글로,슬로스타,우코,슬링키,참새,도리".split(",")
        val split2 = "수아,루루,글로,슬로스타,우코,슬링키,참새,도리,준팍,도밥,고니".split(",")
        val members = Members(split)
        val others = Members(split2)

        // when
        val result = members.compareInAnyOrder(others)

        // then
        assert(result)
    }


    @Test
    fun `compare two member should return false when different members`() {
        // given
        val members = Members(listOf("a", "b", "c", "d", "e"))
        val others = Members(listOf("e", "f", "c", "d", "a"))

        // when
        val result = members.compareInAnyOrder(others)

        // then
        assertFalse(result)
    }
}