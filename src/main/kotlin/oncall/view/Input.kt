package oncall.view

import camp.nextstep.edu.missionutils.Console

object Input {

    const val DAY_OF_WEEK_OFFSET = 1;
    const val MONTH_OFFSET = 0;

    fun enterStartDate() {
        println("비상 근무를 배정할 월과 시작 요일을 입력하세요.")
        val readLine = Console.readLine();
    }

    private fun extractDayOfWeek(readLine: String) : String{
        return readLine.split(" ")[DAY_OF_WEEK_OFFSET]
    }

    private fun extractMonth(readLine: String) : Int {
        try {
            return  readLine.split(" ")[MONTH_OFFSET].toInt()
        } catch (e: NumberFormatException){
            throw IllegalArgumentException("올바르지 않는 숫자 형식입니다.");
        }
    }

    fun enterWeekdayOnCallMember() : List<String>{
        print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요>")
        return Console.readLine().split(" ")
    }

    fun enterWeekendOnCallMember() : List<String>{
        print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요>")
        return Console.readLine().split(" ")
    }
}