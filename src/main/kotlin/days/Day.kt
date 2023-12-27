package days

import utils.InputReader

abstract class Day<T : Any> (val number: Int, val title: String) {
    private val inputList by lazy { InputReader.readAsList(number) }
    abstract fun partOne(input: List<String> = inputList): T
    abstract val partOneTestExamples: Map<List<String>, T>
    protected val test1List by lazy { InputReader.readAsList(number, 1) }

    abstract fun partTwo(input: List<String> = inputList): T
    abstract val partTwoTestExamples: Map<List<String>, T>
    protected val test2List by lazy { InputReader.readAsList(number, 2) }
}