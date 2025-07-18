package oncall.worker

data class Worker(
    val name: String,
) {
    init {
        require(name.length in NAME_MIN_SIZE..NAME_MAX_SIZE) {
            "Name must be between $NAME_MIN_SIZE and $NAME_MAX_SIZE characters. invalid name: $name"
        }
        require(NAME_REGEX.matches(name)) {
            "Name must consist of alphabetic characters only. Invalid name: $name"
        }
    }

    companion object {
        private const val NAME_MIN_SIZE = 2
        private const val NAME_MAX_SIZE = 5
        private val NAME_REGEX = "^[a-zA-Z]+$".toRegex()
    }
}
