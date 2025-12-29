package org

import kotlin.math.abs

fun main() {
    fun parseCommand(line: String): Int {
        val value = line.substring(1).toInt()
        return if (line.startsWith('L')) -value else value
    }
    
    fun balanceCounter(startPos: Int, movement: Int): Pair<Int, Int> {
        val crossings = when {
            movement > 0 -> (startPos + movement) / 100
            movement < 0 -> {
                val absMovement = abs(movement)
                when {
                    startPos == 0 -> absMovement / 100
                    absMovement >= startPos -> ((absMovement - startPos) / 100) + 1
                    else -> 0
                }
            }
            else -> 0
        }

        val finalPos = (startPos + movement) % 100
        return Pair(finalPos, crossings)
    }
    
    fun part1(input: List<String>): Int {
        var pointerValue = 50
        var counter = 0

        input.forEach { line ->
            pointerValue = (pointerValue + parseCommand(line)) % 100
            if (pointerValue == 0) counter++
        }
        return counter
    }

    fun part2(input: List<String>): Int {
        var pointerValue = 50
        var counter = 0

        input.forEach { line ->
            val command = parseCommand(line)
            val (newPos, crossings) = balanceCounter(pointerValue, command)
            pointerValue = newPos
            counter += crossings
        }
        return counter
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 6)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
