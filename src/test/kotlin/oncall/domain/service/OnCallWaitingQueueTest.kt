package oncall.domain.service

import oncall.domain.member.Member
import oncall.domain.member.Members
import org.junit.jupiter.api.Test

class OnCallWaitingQueueTest {

    @Test
    fun `pull member out to queue`() {
        // given
        val queue = OnCallWaitingQueue(Members(listOf("프람", "초롱", "켬미", "제우스", "리사")))

        // when
        val next = queue.next("")

        // then
        assert(next == Member("프람"))
    }

    @Test
    fun `don't bring out the same member twice in a row`() {
        // given
        val queue = OnCallWaitingQueue(Members(listOf("프람", "초롱", "켬미", "제우스", "리사")))

        // when
        val next = queue.next("프람")

        // then
        assert(next == Member("초롱"))
    }
}