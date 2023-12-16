package days

class Day02 : Day(2, "Cube Conundrum") {
    override fun partOne(input: List<String>): Int = getGames(input).filter { (_, triple) ->
        triple.first.second <= 12 && triple.second.second <= 13 && triple.third.second <= 14
    }.sumOf { it.first }
    override fun partTwo(input: List<String>): Int = getGames(input).sumOf { (_, s) ->
        s.first.second * s.second.second * s.third.second
    }

    private fun getGames(input: List<String>): List<Pair<Int, Triple<Pair<String, Int>, Pair<String, Int>, Pair<String, Int>>>> {
        val regex = Regex("\\d+ (blue|green|red)")
        var i = 1
        val games = input.map { line ->
            val parsed = regex.findAll(line).toList().map {
                val split = it.value.split(" ")
                split[1] to split[0].toInt()
            }

            val red = parsed.filter { it.first == "red" }.maxBy { it.second }
            val green = parsed.filter { it.first == "green" }.maxBy { it.second }
            val blue = parsed.filter { it.first == "blue" }.maxBy { it.second }

            i++ to Triple(red, green, blue)
        }
        return games
    }

    override val partOneTestExamples: Map<List<String>, Int> = mapOf(test1List to 8)
    override val partTwoTestExamples: Map<List<String>, Int> = mapOf(test1List to 2286)
}