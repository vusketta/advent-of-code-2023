package days

class Day01 : Day<Int>(1, "Trebuchet?!") {
    override fun partOne(input: List<String>): Int {
        return input.map { line -> line.filter { it.isDigit() } }
            .sumOf { "${it.first()}${it.last()}".toInt() }
    }

    override val partOneTestExamples: Map<List<String>, Int> = mapOf(test1List to 142)

    override fun partTwo(input: List<String>): Int =
        input.map { line -> line.replaceMap(replacements).filter { it.isDigit() } }
            .sumOf { "${it.first()}${it.last()}".toInt() }

    override val partTwoTestExamples: Map<List<String>, Int> = mapOf(test2List to 281)

    private val replacements = mapOf(
        "one" to "1", "two" to "2", "three" to "3", "four" to "4", "five" to "5",
        "six" to "6", "seven" to "7", "eight" to "8", "nine" to "9"
    )

    private fun String.replaceMap(replacements: Map<String, String>): String {
        var result = this
        replacements.forEach { (k, v) -> result = result.replace(k, "$k$v$k") }
        return result
    }

}