package oncall.domain

data class Worker(val name: Name, val isTemporary : Boolean = false)

data class Name(val name: String) {
    init {
        require(name.length in 1..5) {
            throw IllegalArgumentException("Name must be between 1 and 5 digits")
        }
    }
}
