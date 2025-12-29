package org

fun main() {
    
    fun parseCommand(line: String): Int {
        val value = line.substring(1)
        val isNegative = line[0] == 'L'
        return "${if (isNegative) '-' else ""}$value".toInt()
    }
    
    fun balanceCounter1(counter: Int): Int {
        var result = counter
        while (result >= 100) {
            result -= 100
        }
        
        while (result < 0) {
            result += 100
        }
        
        return result
    }
    
    fun balanceCounter2(counter: Int): Pair<Int, Int> {
        var result = counter
        var crossedZeroTimes = 0
        while (result >= 100) {
            result -= 100
            crossedZeroTimes++
        }
        
        while (result < 0) {
            result += 100
            crossedZeroTimes++
        }
        
        if (result == 0 && crossedZeroTimes== 0) {
            crossedZeroTimes= 1
        }

        return Pair(result, crossedZeroTimes)
    }
    
    fun part1(input: List<String>): Int {
        var pointerValue = 50
        var counter = 0
        input.forEach {
            val command = parseCommand(it)
            println("command: $command")
            pointerValue += parseCommand(it)
            pointerValue = balanceCounter1(pointerValue)
            if (pointerValue == 0) {
                counter++
            }
            println("Pointer value: $pointerValue")
            println("Counter: $counter")
        }
        return counter
    }

    fun part2(input: List<String>): Int {
        var pointerValue = 0
        var counter = 0
        input.forEach {
            val command = parseCommand(it)
            println("command: $command")
            pointerValue += command
            val balanceResult = balanceCounter2(pointerValue)
            pointerValue = balanceResult.first
            counter += balanceResult.second

            println("Pointer value: $pointerValue")
            println("Counter: $counter")
        }
        return counter
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    val testInput2 = readInput("Day01_test2")
//    check(part1(testInput) == 3)
//    check(part2(testInput) == 6)
    check(part2(testInput2) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
//    part1(input).println()
//    part2(input).println()
}
