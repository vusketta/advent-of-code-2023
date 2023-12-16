package utils

import days.Day
import org.reflections.Reflections
import kotlin.math.max
import kotlin.time.TimedValue
import kotlin.time.measureTimedValue

object Runner {
    private val reflections = Reflections("days")

    fun run(args: Array<String>) {
        if (args.isEmpty()) {
            val allDayClasses = getAllDayClasses()
            allDayClasses?.sortedBy { dayNumber(it.simpleName) }?.forEach { printDay(it) }
                ?: printError("Couldn't find day classes - make sure you're in the right directory and try building again")
            return
        }

        val day = try {
            args[0].toInt()
        } catch (e: NumberFormatException) {
            printError("Day argument must be an integer")
            return
        }

        val dayClass = getDayClass(day)
        if (dayClass != null) {
            printDay(dayClass)
        } else {
            printError("Day $day not found")
        }
    }

    fun getDay(day: Int) = getDayClass(day)?.constructors?.get(0)?.newInstance() as Day

    private fun getDayClass(day: Int) = getAllDayClasses()?.find { dayNumber(it.simpleName) == day }

    private fun getAllDayClasses(): MutableSet<Class<out Day>>? {
        return reflections.getSubTypesOf(Day::class.java)
    }

    private fun printDay(dayClass: Class<out Day>) {
        val day = dayClass.constructors[0].newInstance() as Day
        println("\n--- Day ${dayNumber(dayClass.simpleName)}: ${day.title} ---")

        val partOne = measureTimedValue { day.partOne() }
        val partTwo = measureTimedValue { day.partTwo() }
        printParts(partOne, partTwo)
    }

    private fun printParts(partOne: TimedValue<Int>, partTwo: TimedValue<Int>) {
        val padding = max(partOne.value.toString().length, partTwo.value.toString().length) + 14
        println("Part 1: ${partOne.value}".padEnd(padding, ' ') + "(${partOne.duration})")
        println("Part 2: ${partTwo.value}".padEnd(padding, ' ') + "(${partTwo.duration})")
    }

    private fun printError(message: String) {
        System.err.println("\n=== ERROR ===\n$message")
    }

    private fun dayNumber(dayClassName: String) = dayClassName.replace("Day", "").toInt()
}