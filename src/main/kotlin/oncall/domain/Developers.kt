package oncall.domain

data class Developers(
    private val developers: List<Developer>,
    private var nextIndex: Int = 0
) {
    init {
        require(developers.size in MINIMUM_SIZE..MAXIMUM_SIZE) { "Number of developers must be between $MINIMUM_SIZE and $MAXIMUM_SIZE." }
        require(developers.distinct().size == developers.size) { "Developer names must not be duplicated." }
    }

    fun next(): Developer {
        val developer = developers[nextIndex]
        nextIndex = (nextIndex + 1) % developers.size
        return developer
    }

    fun rearrange(developer: Developer): Developers {
        val idx = developers.indexOf(developer)
        val swapIdx = (idx + 1) % developers.size
        val newList = developers.toMutableList().apply {
            val temp = this[idx]
            this[idx] = this[swapIdx]
            this[swapIdx] = temp
        }
        return Developers(newList)
    }

    fun hasSameDevelopers(other: Developers): Boolean {
        val devSet = developers.toSet()
        val otherDevSet = other.developers.toSet()
        return devSet.size == otherDevSet.size
                && (devSet + otherDevSet).size == devSet.size
    }

    companion object {
        private const val MINIMUM_SIZE = 5
        private const val MAXIMUM_SIZE = 35
    }
} 