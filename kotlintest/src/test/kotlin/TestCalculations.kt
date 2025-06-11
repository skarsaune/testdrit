import no.kantega.multiply
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.provider.CsvSource
import java.util.stream.Stream

class TestCalculations {
    @TestFactory
    fun dynamicTestMultiplication(): List<DynamicTest> {

        println("Before tests")
        val tests = testCases().map { testCase ->
            dynamicTest(testCase.toString()) {
                runTestCase(testCase)
            }
        }
        println("After tests")
        return tests
    }

    data class TestCase(
        val givenFirstFactor: Int,
        val givenSecondFactor: Int,
        val thenExpectResult: Int
    ) {
        override fun toString():String = "Verify that $givenFirstFactor * $givenSecondFactor = $thenExpectResult"
    }



    @ParameterizedTest()
//    @ParameterizedTest(name = "Verifying that multiplication of {0} * {1} = {2}")
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

    class TestParameters : ArgumentsProvider {
        override fun provideArguments(p0: ExtensionContext?): Stream<out Arguments> {
            return testCases().map { Arguments.of(it)}.stream()
        }
    }

    @ParameterizedTest()
    @ArgumentsSource(TestParameters::class)
    fun runTestCase(testCase: TestCase) {
        println("Running test ${testCase}")
        Assertions.assertEquals(
            testCase.thenExpectResult,
            multiply(testCase.givenFirstFactor, testCase.givenSecondFactor)
        )
    }

    companion object {
        fun testCases() = arrayOf(
            TestCase(
                givenFirstFactor = 1,
                givenSecondFactor = 2,
                thenExpectResult = 2
            ),
            TestCase(
                givenFirstFactor = 2,
                givenSecondFactor = 2,
                thenExpectResult = 4
            ),
            TestCase(
                givenFirstFactor = 2,
                givenSecondFactor = 3,
                thenExpectResult = 6
            ),
        )
    }


}