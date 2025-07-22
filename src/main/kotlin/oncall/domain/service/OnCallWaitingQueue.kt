package oncall.domain.service

import oncall.domain.member.Member
import oncall.domain.member.Members

class OnCallWaitingQueue(val members: Members, var index: Int = 0) {

    fun next(): Member {
        return members[index++ % members.size]
    }
}