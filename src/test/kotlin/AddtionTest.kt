import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

class AddtionTest {
    @Test
    fun add_two_num() {
        expectThat(5 + 6).isEqualTo(11)
        expectThat(42 + 9).isEqualTo(51)
    }
}
