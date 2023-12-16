package days

import kotlin.math.pow

class Day04 : Day(4, "Scratchcards") {
    override fun partOne(input: List<String>): Int = input.sumOf { getLinePoints(it) }
    override fun partTwo(input: List<String>): Int = getScratchCardsTotal(input)

    private fun parseCard(line: String): Set<String> {
        val (winning, scratch) = line.replace(Regex("\\s+"), " ")
            .replace(Regex("Card \\d+:"), "")
            .split("|")
        return winning.split(" ").filter { it.isNotEmpty() }
            .intersect(scratch.split(" ").filter { it.isNotEmpty() }.toSet())
    }
    private fun getLinePoints(line: String): Int = 2.0.pow(parseCard(line).size.toDouble() - 1).toInt()
    private fun getScratchCardsTotal(input: List<String>): Int {
        val cards = List(input.size) { 1 }.toMutableList()
        val matches = input.map { parseCard(it) }
        for (i in input.indices) {
            for (j in matches[i].indices) {
                cards[i + j + 1] += cards[i]
            }
        }
        return cards.sum()
    }

    override val partOneTestExamples: Map<List<String>, Int> = mapOf(test1List to 13)
    override val partTwoTestExamples: Map<List<String>, Int> = mapOf(test1List to 30)
}