package oncall.domain

data class Developer(val name: String) {
    init {
        require(name.trim().length in (MINIMUM_NAME_LENGTH..MAXIMUM_NAME_LENGTH)) {
            "Developer name must be less than or equal to $MAXIMUM_NAME_LENGTH characters."
        }
    }

    override fun equals(other: Any?): Boolean {
        return name == (other as Developer).name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    companion object {
        private const val MINIMUM_NAME_LENGTH = 1
        private const val MAXIMUM_NAME_LENGTH = 5
    }
}
