package utils

import java.io.File

object InputReader {

    fun readAsList(day: Int): List<String> = fromResources("input_day_$day.txt").readLines()
    fun readAsList(day: Int, part: Int): List<String> = File("src/test/resources/test${part}_day_${day}.txt").readLines()

    private fun fromResources(string: String): File {
        return File(javaClass.classLoader.getResource(string)!!.toURI())
    }
}