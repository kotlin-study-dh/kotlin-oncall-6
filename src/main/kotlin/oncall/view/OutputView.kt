package oncall.view

import oncall.DailyOnCallAssignment
import oncall.toStringDayOfWeek

fun printOnCallWorkers(onCallAssignment: List<DailyOnCallAssignment>) {
    onCallAssignment.forEach {
        println(
            "${it.onCallDate.month.value}월 " +
                    "${it.onCallDate.day}일 " +
                    "${toStringDayOfWeek(it.onCallDate.dayOfWeek)} ${it.worker.name.name}"
        )
    }
}
