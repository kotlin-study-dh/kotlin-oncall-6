package oncall.view

import camp.nextstep.edu.missionutils.Console

object Input {

    private const val DAY_OF_WEEK_OFFSET = 1
    private const val MONTH_OFFSET = 0

    fun enterStartDate(): Pair<String, Int> {
        println("비상 근무를 배정할 월과 시작 요일을 입력하세요.")
        val readLine = Console.readLine().replace(" ", "")
        return Pair(extractDayOfWeek(readLine), extractMonth(readLine))
    }

    private fun extractDayOfWeek(readLine: String): String {
        val split = readLine.split(",")
        if (split.size != 2) {
            throw IllegalArgumentException("올바른 형식으로 다시 입력해주세요.")
        }
        return split[DAY_OF_WEEK_OFFSET]
    }

    private fun extractMonth(readLine: String): Int {
        val convert = readLine.split(",")[MONTH_OFFSET].toIntOrNull()
        require(convert in 1..12 || convert != null) {
            throw IllegalArgumentException("올바르지 않은 월입니다.")
        }
        return convert!!
    }

    fun enterWeekdayOnCallMember(): List<String> {
        print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        return Console.readLine().split(",")
    }

    fun enterHolidayOnCallMember(): List<String> {
        print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        return Console.readLine().split(",")
    }
}