import strikt.api.expect
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.random.Random
import kotlin.test.Test

class AddtionTest {

    fun randomNatural() = Random.nextInt(from=1, until = 100_000_000)


    @Test
    fun add_two_num() {
        expectThat(5 + 6).isEqualTo(11)
        expectThat(42 + 9).isEqualTo(51)
        expectThat(9999 +1).isEqualTo(10000);
    }

    @Test
    fun zero_identity() {
        repeat(100) {
            val x = randomNatural()
            expectThat(x + 0).isEqualTo(x);
        }
    }

    @Test
    fun commutative_property() {
        repeat(100) {
            val x = randomNatural()
            val y = randomNatural()
            expectThat(x + y).isEqualTo(y+x);
        }
    }

    @Test
    fun assosiative_property() {
        repeat(100) {
            val x = randomNatural()
            val y = randomNatural()
            val z = randomNatural()
            expect {
                that((x+y)+z).isEqualTo(x + (y+z));
                that((y+z) +x).isEqualTo(y + (z+x));
                that((z+x) +y).isEqualTo(z + (x+y));
            }
        }
    }

    @Test
    fun adding_collection() {
       repeat(100) {
            val x = randomNatural()
            val y = Random.nextInt(1, 100)
            val ones = List(y) { 1 } // list of ones with size of y
            val z = ones.fold(x) { acc, one -> acc + one } // fold takes an initial value(x) and applies an operation to each element of a collection, accumulating a single result
            expectThat(z).isEqualTo(x + y)
        }
    }
}
