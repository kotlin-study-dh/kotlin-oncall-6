package oncall.view

import camp.nextstep.edu.missionutils.Console

object InputView {
    private const val SHIFT_MONTH_AND_START_DAY_OF_WEEK_DELIMITER = ","
    private const val SHIFT_MONTH_AND_START_DAY_OF_WEEK_SIZE = 2
    private const val MONTH_INDEX = 0
    private const val DAY_OF_WEEK_INDEX = 1
    private const val WORKERS_DELIMITER = ","

    fun readShiftMonthAndStartDayOfWeek(): Pair<Int, String> {
        print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
        val input = Console.readLine() ?: throw IllegalArgumentException("Month and day of week are required.")
        val parts = input.split(SHIFT_MONTH_AND_START_DAY_OF_WEEK_DELIMITER).also {
            require(it.size == SHIFT_MONTH_AND_START_DAY_OF_WEEK_SIZE) { "Input must be in the format 'month,dayOfWeek'" }
        }
        val month = parts[MONTH_INDEX].toIntOrNull() ?: throw IllegalArgumentException("Month must be a number.")
        val dayOfWeek = parts[DAY_OF_WEEK_INDEX].takeIf { it.isNotBlank() }
            ?: throw IllegalArgumentException("Day of week cannot be blank.")
        return month to dayOfWeek
    }

    fun readWorkingDayShiftWorkerNames(): List<String> {
        print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        val input = Console.readLine() ?: throw IllegalArgumentException("Worker names are required.")
        return input.split(WORKERS_DELIMITER).map { it.trim() }
    }

    fun readNonWorkingDayShiftWorkerNames(): List<String> {
        print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        val input = Console.readLine() ?: throw IllegalArgumentException("Worker names are required.")
        return input.split(WORKERS_DELIMITER).map { it.trim() }
    }
}
