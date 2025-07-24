package oncall.shift

import oncall.shift.ShiftWorkers.NonWorkingDayShiftWorkers
import oncall.shift.ShiftWorkers.WorkingDayShiftWorkers
import oncall.worker.Worker
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ShiftSchedulerTest {
    private val shiftScheduler = ShiftScheduler()

    @Test
    fun `should fails to create shift schedule when working day shift workers are not contained in non-working day shift workers`() {
        // given
        val workingDayShiftWorkers = WorkingDayShiftWorkers.from(
            listOf("Capy", "Rosie", "Pram", "Tre", "Kriby", "Prin")
        )
        val nonWorkingDayShiftWorkers = NonWorkingDayShiftWorkers.from(
            listOf("Capy", "Rosie", "Pram", "Tre", "Kriby")
        )
        val startDate = LocalDate.of(2023, 5, 2)

        // when & then
        assertThatThrownBy { shiftScheduler.create(workingDayShiftWorkers, nonWorkingDayShiftWorkers, startDate) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("working day shift workers must be assigned to non-working day shift workers and vice versa.")
    }

    @Test
    fun `should fails to create shift schedule when non-working day shift workers are not contained in working day shift workers`() {
        // given
        val workingDayShiftWorkers = WorkingDayShiftWorkers.from(
            listOf("Capy", "Rosie", "Pram", "Tre", "Kriby")
        )
        val nonWorkingDayShiftWorkers = NonWorkingDayShiftWorkers.from(
            listOf("Capy", "Rosie", "Pram", "Tre", "Kriby", "Prin")
        )

        val startDate = LocalDate.of(2023, 5, 2)

        // when & then
        assertThatThrownBy { shiftScheduler.create(workingDayShiftWorkers, nonWorkingDayShiftWorkers, startDate) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("working day shift workers must be assigned to non-working day shift workers and vice versa.")
    }

    @Test
    fun `should create shift schedule for given month and day of week (MAY)`() {
        // given
        val workingDayShiftWorkers = WorkingDayShiftWorkers.from(
            listOf("Capy", "Rosie", "Prin", "Pram", "Tre", "Kirby", "Choco", "Rush", "Zeus", "Dora", "Jerry")
        )
        val nonWorkingDayShiftWorkers = NonWorkingDayShiftWorkers.from(
            listOf("Pram", "Tre", "Kirby", "Choco", "Rush", "Zeus", "Dora", "Jerry", "Capy", "Rosie", "Prin")
        )
        val startDate = LocalDate.of(2023, 5, 1)

        // when
        val schedule = shiftScheduler.create(workingDayShiftWorkers, nonWorkingDayShiftWorkers, startDate)

        // then
        assertThat(schedule).containsExactlyEntriesOf(
            mapOf(
                LocalDate.of(2023, 5, 1) to Worker("Capy"),
                LocalDate.of(2023, 5, 2) to Worker("Rosie"),
                LocalDate.of(2023, 5, 3) to Worker("Prin"),
                LocalDate.of(2023, 5, 4) to Worker("Pram"),
                LocalDate.of(2023, 5, 5) to Worker("Tre"),
                LocalDate.of(2023, 5, 6) to Worker("Pram"),
                LocalDate.of(2023, 5, 7) to Worker("Kirby"),
                LocalDate.of(2023, 5, 8) to Worker("Tre"),
                LocalDate.of(2023, 5, 9) to Worker("Kirby"),
                LocalDate.of(2023, 5, 10) to Worker("Choco"),
                LocalDate.of(2023, 5, 11) to Worker("Rush"),
                LocalDate.of(2023, 5, 12) to Worker("Zeus"),
                LocalDate.of(2023, 5, 13) to Worker("Choco"),
                LocalDate.of(2023, 5, 14) to Worker("Rush"),
                LocalDate.of(2023, 5, 15) to Worker("Dora"),
                LocalDate.of(2023, 5, 16) to Worker("Jerry"),
                LocalDate.of(2023, 5, 17) to Worker("Capy"),
                LocalDate.of(2023, 5, 18) to Worker("Rosie"),
                LocalDate.of(2023, 5, 19) to Worker("Prin"),
                LocalDate.of(2023, 5, 20) to Worker("Zeus"),
                LocalDate.of(2023, 5, 21) to Worker("Dora"),
                LocalDate.of(2023, 5, 22) to Worker("Pram"),
                LocalDate.of(2023, 5, 23) to Worker("Tre"),
                LocalDate.of(2023, 5, 24) to Worker("Kirby"),
                LocalDate.of(2023, 5, 25) to Worker("Choco"),
                LocalDate.of(2023, 5, 26) to Worker("Rush"),
                LocalDate.of(2023, 5, 27) to Worker("Jerry"),
                LocalDate.of(2023, 5, 28) to Worker("Capy"),
                LocalDate.of(2023, 5, 29) to Worker("Zeus"),
                LocalDate.of(2023, 5, 30) to Worker("Dora"),
                LocalDate.of(2023, 5, 31) to Worker("Jerry")
            )
        )
    }

    @Test
    fun `should create shift schedule for given month and day of week (OCT)`() {
        // given
        val workingDayShiftWorkers = WorkingDayShiftWorkers.from(
            listOf("Capy", "Tre", "Rosie", "Kirby", "Pram")
        )
        val nonWorkingDayShiftWorkers = NonWorkingDayShiftWorkers.from(
            listOf("Pram", "Tre", "Kirby", "Capy", "Rosie")
        )
        val startDate = LocalDate.of(2023, 10, 2)

        // when
        val schedule = shiftScheduler.create(workingDayShiftWorkers, nonWorkingDayShiftWorkers, startDate)

        // then
        assertThat(schedule).containsExactlyEntriesOf(
            mapOf(
                LocalDate.of(2023, 10, 2) to Worker("Capy"),
                LocalDate.of(2023, 10, 3) to Worker("Pram"),
                LocalDate.of(2023, 10, 4) to Worker("Tre"),
                LocalDate.of(2023, 10, 5) to Worker("Rosie"),
                LocalDate.of(2023, 10, 6) to Worker("Kirby"),
                LocalDate.of(2023, 10, 7) to Worker("Tre"),
                LocalDate.of(2023, 10, 8) to Worker("Kirby"),
                LocalDate.of(2023, 10, 9) to Worker("Capy"),
                LocalDate.of(2023, 10, 10) to Worker("Pram"),
                LocalDate.of(2023, 10, 11) to Worker("Capy"),
                LocalDate.of(2023, 10, 12) to Worker("Tre"),
                LocalDate.of(2023, 10, 13) to Worker("Rosie"),
                LocalDate.of(2023, 10, 14) to Worker("Pram"),
                LocalDate.of(2023, 10, 15) to Worker("Rosie"),
                LocalDate.of(2023, 10, 16) to Worker("Kirby"),
                LocalDate.of(2023, 10, 17) to Worker("Pram"),
                LocalDate.of(2023, 10, 18) to Worker("Capy"),
                LocalDate.of(2023, 10, 19) to Worker("Tre"),
                LocalDate.of(2023, 10, 20) to Worker("Rosie"),
                LocalDate.of(2023, 10, 21) to Worker("Tre"),
                LocalDate.of(2023, 10, 22) to Worker("Kirby"),
                LocalDate.of(2023, 10, 23) to Worker("Pram"),
                LocalDate.of(2023, 10, 24) to Worker("Kirby"),
                LocalDate.of(2023, 10, 25) to Worker("Capy"),
                LocalDate.of(2023, 10, 26) to Worker("Tre"),
                LocalDate.of(2023, 10, 27) to Worker("Rosie"),
                LocalDate.of(2023, 10, 28) to Worker("Capy"),
                LocalDate.of(2023, 10, 29) to Worker("Rosie"),
                LocalDate.of(2023, 10, 30) to Worker("Kirby"),
                LocalDate.of(2023, 10, 31) to Worker("Pram")
            )
        )
    }
}
