package oncall.domain.service

import oncall.domain.member.Member
import oncall.domain.member.Members

data class OnCallWaitingQueue(val members: Members, var index: Int = 0, var lastMember: Member? = null) {

    fun next(prevMemberName: String): Member {
        val prevMember = Member(prevMemberName)
        if (prevMember == members[index % members.size]) {
            lastMember = members[(index + 1) % members.size]
            return members[(index + 1) % members.size]
        }
        if (lastMember == members[(index + 1) % members.size]) {
            val next = members[index % members.size]
            lastMember = next
            index += 2
            return next
        }
        lastMember = members[index % members.size]
        return members[index++ % members.size]
    }
}