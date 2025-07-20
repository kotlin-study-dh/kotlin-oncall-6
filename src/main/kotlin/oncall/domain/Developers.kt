package oncall.domain

class Developers(val developers: List<Developer>) {

   init {
        require(developers.size in 5..35) { "Number of developers must be between 5 and 35." }
        require(developers.distinct().size == developers.size) { "Developer names must not be duplicated." }
    }


}