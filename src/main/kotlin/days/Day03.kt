package days

class Day03 : Day<Int>(3, "Gear Ratios") {
    override fun partOne(input: List<String>): Int = getPartNumbers(input).flatten().sumOf { it.first }

    override val partOneTestExamples: Map<List<String>, Int> = mapOf(test1List to 4361)

    override fun partTwo(input: List<String>): Int {
        val partNumbers = getPartNumbers(input)
        return input.mapIndexed { i, s -> getGearsIndex(s).map { getGearsRatio(i, it, partNumbers) } }.flatten().sum()
    }

    override val partTwoTestExamples: Map<List<String>, Int> = mapOf(test1List to 467835)

    private fun getPartNumbers(input: List<String>) = input.mapIndexed { i, s ->
        Regex("\\d+").findAll(s).toList()
            .map { it.value.toInt() to it.range }
            .filter { checkPartNumber(i, it.second, input) }
    }

    private fun checkPartNumber(i: Int, range: IntRange, input: List<String>): Boolean {
        range.forEach { j ->
            if (checkPartNumber(i, j, input)) return true
        }
        return false
    }

    private fun checkPartNumber(i: Int, j: Int, input: List<String>): Boolean {
        if (0 <= i - 1 && 0 <= j - 1) {
            val ch = input[i - 1][j - 1]
            if (!ch.isLetterOrDigit() && ch != '.' && ch != ' ') return true
        }
        if (0 <= i - 1) {
            val ch = input[i - 1][j]
            if (!ch.isLetterOrDigit() && ch != '.' && ch != ' ') return true
        }
        if (0 <= i - 1 && j + 1 < input[i - 1].length) {
            val ch = input[i - 1][j + 1]
            if (!ch.isLetterOrDigit() && ch != '.' && ch != ' ') return true
        }
        if (i + 1 < input.size && 0 <= j - 1) {
            val ch = input[i + 1][j - 1]
            if (!ch.isLetterOrDigit() && ch != '.' && ch != ' ') return true
        }
        if (i + 1 < input.size) {
            val ch = input[i + 1][j]
            if (!ch.isLetterOrDigit() && ch != '.' && ch != ' ') return true
        }
        if (i + 1 < input.size && j + 1 < input[i + 1].length) {
            val ch = input[i + 1][j + 1]
            if (!ch.isLetterOrDigit() && ch != '.' && ch != ' ') return true
        }
        if (0 <= j - 1) {
            val ch = input[i][j - 1]
            if (!ch.isLetterOrDigit() && ch != '.' && ch != ' ') return true
        }
        if (j + 1 < input[i].length) {
            val ch = input[i][j + 1]
            if (!ch.isLetterOrDigit() && ch != '.' && ch != ' ') return true
        }
        return false
    }

    private fun getGearsIndex(line: String): List<Int> = Regex("\\*").findAll(line).map { it.range.first }.toList()

    private fun getGearsRatio(i: Int, j: Int, partNumbers: List<List<Pair<Int, IntRange>>>): Int {
        val adjacentPartNumbers = mutableListOf<Int>()
        val firstRow = if (i - 1 !in partNumbers.indices) listOf() else partNumbers[i - 1]
        val secondRow = partNumbers[i]
        val thirdRow = if (i + 1 !in partNumbers.indices) listOf() else partNumbers[i + 1]
        adjacentPartNumbers.addAll(firstRow.filter { j - 1 in it.second || j in it.second || j + 1 in it.second }.map { it.first })
        adjacentPartNumbers.addAll(secondRow.filter { j - 1 in it.second || j in it.second || j + 1 in it.second }.map { it.first })
        adjacentPartNumbers.addAll(thirdRow.filter { j - 1 in it.second || j in it.second || j + 1 in it.second }.map { it.first })
        return if (adjacentPartNumbers.size != 2) 0 else adjacentPartNumbers[0] * adjacentPartNumbers[1]
    }
}