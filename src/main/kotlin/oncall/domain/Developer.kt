package oncall.domain

data class Developer(val name: String) {
    init {
        require(name.trim().length in (1..5)) { "Developer name must be less than or equal to 5 characters." }
    }

    override fun equals(other: Any?): Boolean {
        return name == (other as Developer).name
    }
}
