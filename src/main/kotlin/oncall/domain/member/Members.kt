package oncall.domain.member

class Members(names: List<String>) {

    private val members: List<Member>

    init {
        require(names.size in MIN_MEMBER_LENGTH..MAX_MEMBER_LENGTH) {
            "valid name range is between $MIN_MEMBER_LENGTH and $MAX_MEMBER_LENGTH"
        }
        require(names.size == names.distinct().size) {
            "duplicate member names are not allowed"
        }
        members = names.map { Member(it) }
    }

    val size: Int
        get() = members.size

    operator fun get(index: Int): Member {
        return members[index]
    }

    fun compareInAnyOrder(others: Members): Boolean {
        val distinct = others.members.distinct()
        if (size != distinct.size) {
            return false
        }
        for (member in members) {
            if (!others.members.contains(member)) {
                return false
            }
        }
        return true
    }

    companion object {
        const val MIN_MEMBER_LENGTH = 5
        const val MAX_MEMBER_LENGTH = 35
    }
}