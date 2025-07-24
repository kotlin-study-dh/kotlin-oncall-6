package oncall

fun main() {
    val onCallService = OnCallService()
    OnCallController(onCallService).run()
}
