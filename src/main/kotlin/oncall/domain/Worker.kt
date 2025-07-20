package oncall.domain

data class Worker(val name: Name)

data class Name(val name: String) {
    init {
        require(name.length in 1..5)
    }
}
