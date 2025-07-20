package oncall.domain

class OnCallOrder(val developers: List<Developer>) {

   init {
       require(developers.size in 5..35) {
           "Number of developers must be between $MINIMUM_NUMBER_OF_DEVELOPERS and $MAXIMUM_NUMBER_OF_DEVELOPERS."
       }
        require(developers.distinct().size == developers.size) { "Developer names must not be duplicated." }
    }

    fun rearrange(developer: Developer): OnCallOrder {
        val indexOfTarget = developers.indexOf(developer)
        val indexOf = indexOfTarget.inc() % developers.size
        val swapped = developers.swap(indexOfTarget, indexOf)
        return OnCallOrder(swapped)
    }

    companion object {
        private const val MINIMUM_NUMBER_OF_DEVELOPERS = 5;
        private const val MAXIMUM_NUMBER_OF_DEVELOPERS = 35;
    }

}

fun List<Developer>.swap(index1: Int, index2: Int): List<Developer> {
    val mutableDevelopers = this.toMutableList()
    mutableDevelopers[index1] = this[index2]
    mutableDevelopers[index2] = this[index1]
    return mutableDevelopers.toList()
}