package oncall.domain.member

data class Member(val name: String) {

    init {
        require(name.length <= MAX_NAME_LENGTH) {
            "The length of member must be less than $MAX_NAME_LENGTH"
        }
    }

    companion object {
        const val MAX_NAME_LENGTH = 5
    }
}