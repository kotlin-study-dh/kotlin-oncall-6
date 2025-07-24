package oncall

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ApplicationTest : NsTest() {
    @Test
    fun `exception test`() {
        assertSimpleTest {
            run(
                "0,Sun",
                "4,Sat",
                "Herb,Juni,Soft,Raon,Hena,Ukko,Edan,SD,Power,Heero,Mako,SK,Modi,Salm,zzang,Rio,Goni,Boxt,Dalli,Joy,Noize,Doi,Dochi,HG,Scott,Polo,Hash,Logi,Chex,Ike,Uga,Poom,Ash,Royce,Ocean",
                "Ocean,Royce,Ash,Poom,Uga,Ike,Chex,Logi,Hash,Polo,Scott,HG,Dochi,Doi,Noize,Joy,Dalli,Boxt,Goni,Rio,zzang,Salm,Modi,SK,Mako,Heero,Power,SD,Edan,Ukko,Hena,Raon,Soft,Juni,Herb"
            )
            assertThat(output()).contains(
                ERROR,
                """
                April 1 Sat Ocean
                April 2 Sun Royce
                April 3 Mon Herb
                April 4 Tue Juni
                April 5 Wed Soft
                """.trimIndent()
            )
        }
    }

    @Test
    fun `feature test`() {
        assertSimpleTest {
            run(
                "4,Sat",
                "Herb,Juni,Soft,Raon,Hena,Ukko,Edan,SD,Power,Heero,Mako,SK,Modi,Salm,zzang,Rio,Goni,Boxt,Dalli,Joy,Noize,Doi,Dochi,HG,Scott,Polo,Hash,Logi,Chex,Ike,Uga,Poom,Ash,Royce,Ocean",
                "Ocean,Royce,Ash,Poom,Uga,Ike,Chex,Logi,Hash,Polo,Scott,HG,Dochi,Doi,Noize,Joy,Dalli,Boxt,Goni,Rio,zzang,Salm,Modi,SK,Mako,Heero,Power,SD,Edan,Ukko,Hena,Raon,Soft,Juni,Herb"
            )
            assertThat(output()).contains(
                """
                April 1 Sat Ocean
                April 2 Sun Royce
                April 3 Mon Herb
                April 4 Tue Juni
                April 5 Wed Soft
                April 6 Thu Raon
                April 7 Fri Hena
                April 8 Sat Ash
                April 9 Sun Poom
                April 10 Mon Ukko
                April 11 Tue Edan
                April 12 Wed SD
                April 13 Thu Power
                April 14 Fri Heero
                April 15 Sat Uga
                April 16 Sun Ike
                April 17 Mon Mako
                April 18 Tue SK
                April 19 Wed Modi
                April 20 Thu Salm
                April 21 Fri zzang
                April 22 Sat Chex
                April 23 Sun Logi
                April 24 Mon Rio
                April 25 Tue Goni
                April 26 Wed Boxt
                April 27 Thu Dalli
                April 28 Fri Joy
                April 29 Sat Hash
                April 30 Sun Polo
                """.trimIndent()
            )
        }
    }

    public override fun runMain() {
        main()
    }

    companion object {
        private const val ERROR = "[ERROR]"
    }
}