package days

import utils.InputReader

abstract class Day(val number: Int, val title: String) {
    private val inputList by lazy { InputReader.readAsList(number) }
    protected val test1List by lazy { InputReader.readAsList(number, 1) }
    protected val test2List by lazy { InputReader.readAsList(number, 2) }

    abstract fun partOne(input: List<String> = inputList): Int
    abstract fun partTwo(input: List<String> = inputList): Int

    abstract val partOneTestExamples: Map<List<String>, Int>
    abstract val partTwoTestExamples: Map<List<String>, Int>
}