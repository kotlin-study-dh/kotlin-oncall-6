package oncall.worker

class Worker(
    val name: String,
) {
    init {
        require(name.length in NAME_MIN_SIZE..NAME_MAX_SIZE) {
            "Name must be between 2 and 5 characters. invalid name: $name"
        }
    }

    companion object {
        private const val NAME_MIN_SIZE = 2
        private const val NAME_MAX_SIZE = 5
    }
}
