package oncall.view

import camp.nextstep.edu.missionutils.Console

fun readOnCallMonthAndStartDay(): Pair<Int, String> {
    print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
    val input = Console.readLine()
    require(input.isNotBlank()) {
        IllegalArgumentException("Input should not be blank")
    }

    val parts = input.replace(" ", "")
        .split(",")

    require(parts.size == 2) {
        IllegalArgumentException("Enter correct format")
    }

    val month = parts[0].toIntOrNull() ?: throw IllegalArgumentException("Month must be an integer")
    val day = parts[1]

    return Pair(month, day)
}

fun readWeekdayOnCallWorkers(): List<String> {
    print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
    val input = Console.readLine()
    require(input.isNotBlank()) {
        IllegalArgumentException("Input should not be blank")
    }

    return input.replace(" ", "")
        .split(",")
}

fun readHolidayOnCallWorkers(): List<String> {
    print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
    val input = Console.readLine()
    require(input.isNotBlank()) {
        IllegalArgumentException("Input should not be blank")
    }

    return input.replace(" ", "")
        .split(",")
}
