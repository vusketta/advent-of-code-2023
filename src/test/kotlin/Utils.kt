import org.junit.jupiter.api.Assertions
import utils.Runner

fun parseTestCase(testCase: Map.Entry<List<String>, Int>) = testCase.key to testCase.value

fun parseTestCases(testCases: Map<List<String>, Int>) = testCases.entries.map { parseTestCase(it) }.unzip()

fun testDay(number: Int) {
    val day = Runner.getDay(number)

    val (input1, output1) = parseTestCases(day.partOneTestExamples)
    checkTestCase(input1.map { day.partOne(it) }, output1)

    val (input2, output2) = parseTestCases(day.partTwoTestExamples)
    checkTestCase(input2.map { day.partTwo(it) }, output2)
}

private fun checkTestCase(actual: List<Int>, expected: List<Int>) {
    actual.indices.forEach { i ->
        Assertions.assertEquals(expected[i], actual[i])
    }
}