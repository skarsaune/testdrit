import no.kantega.multiply
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class TestCalculations {
    @TestFactory
    fun dynamicTestMultiplication(): List<DynamicTest> {

        data class TestCase(
            val name: String,
            val givenFirstFactor: Int,
            val givenSecondFactor: Int,
            val thenExpectResult: Int
        )

        println("Before tests")
        val testCases = arrayOf(
            TestCase(
                name = "One by two is two",
                givenFirstFactor = 1,
                givenSecondFactor = 2,
                thenExpectResult = 2
            ),
            TestCase(
                name = "Two by two is four",
                givenFirstFactor = 2,
                givenSecondFactor = 2,
                thenExpectResult = 4
            ),
        )
        val tests = testCases.map { testCase ->
            dynamicTest(testCase.name) {
                println("Running test ${testCase.name}")
                Assertions.assertEquals(
                    testCase.thenExpectResult,
                    multiply(testCase.givenFirstFactor, testCase.givenSecondFactor)
                )
            }
        }
        println("After tests")
        return tests
    }

    @ParameterizedTest()
//    @ParameterizedTest(name = "Verifying multiplication of {0} * {1} = {2}")
    @CsvSource(
        value =
        [
            "1, 2,  2",
            "2, 2,  4",
            "2, 3,  6",
        ],
    )
    fun staticTestInput(givenFirstFactor: Int, givenSecondFactor: Int, thenExpectResult: Int) {
        Assertions.assertEquals(
            thenExpectResult,
            multiply(givenFirstFactor, givenSecondFactor)
        )
    }
}