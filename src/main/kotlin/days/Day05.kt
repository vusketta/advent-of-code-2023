package days

class Day05 : Day<Long>(5, "If You Give A Seed A Fertilizer") {
    override fun partOne(input: List<String>): Long {
        val seeds = getSeeds(input)
        return 0
    }

    override val partOneTestExamples: Map<List<String>, Long> = mapOf(test1List to 35)

    override fun partTwo(input: List<String>): Long = 0

    override val partTwoTestExamples: Map<List<String>, Long> = mapOf(test1List to 142)

    private fun getSeeds(input: List<String>): List<Long> =
        input[0].filterNot { it.isLetter() || it == ':' }.split(Regex("\\s+")).map { it.toLong() }

    private data class Range(val dst: Long, val src: Long, val len: Long)
    private data class Dst(val name: String, val ranges: List<Range>)

}