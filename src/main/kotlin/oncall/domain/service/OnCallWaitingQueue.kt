package oncall.domain.service

import oncall.domain.member.Member
import oncall.domain.member.Members

class OnCallWaitingQueue(val members: Members, var index: Int = 0) {

    fun next(prevMemberName: String): Member {
        val prevMember = Member(prevMemberName)
        if (prevMember == members[index % members.size]) {
            return members[(index + 1) % members.size]
        }
        if (prevMember == members[(index + 1) % members.size]) {
            val next = members[index % members.size]
            index += 2
            return next
        }
        return members[index++ % members.size]
    }
}