package oncall.domain

class Members(names: List<String>) {

    val members: List<Member>

    init {
        require(names.size in MIN_MEMBER_LENGTH..MAX_MEMBER_LENGTH) {
            "valid name range is between $MIN_MEMBER_LENGTH and $MAX_MEMBER_LENGTH"
        }
        require(names.size == names.distinct().size) {
            "duplicate member names are not allowed"
        }
        members = names.map { Member(it) }
    }

    companion object {
        const val MIN_MEMBER_LENGTH = 5
        const val MAX_MEMBER_LENGTH = 35
    }
}