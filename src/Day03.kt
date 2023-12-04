
fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        for (lineIndex in input.indices) {
            val line = input[lineIndex]
            var index = 0
            while (index < line.length) {
                var number = 0
                var numberIndex = index
                while (numberIndex < line.length && line[numberIndex] in '0'..'9') {
                    number = number * 10 + (line[numberIndex] - '0')
                    numberIndex++
                }
                if (number > 0) {
                    var found = false
                    for (x in (index - 1).coerceAtLeast(0)..(numberIndex).coerceAtMost(line.length - 1)) {
                        for (y in (lineIndex - 1).coerceAtLeast(0)..(lineIndex + 1).coerceAtMost(input.size - 1)) {
                            if (input[y][x] != '.' && input[y][x] !in '0'..'9') {
                                found = true
                                break
                            }
                        }
                    }
                    if (found) {
                        sum += number
                    }
                    index = numberIndex
                } else {
                    index++
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val gears = mutableMapOf<Pair<Int, Int>, MutableList<Int>>()
        for (lineIndex in input.indices) {
            val line = input[lineIndex]
            var index = 0
            while (index < line.length) {
                var number = 0
                var numberIndex = index
                while (numberIndex < line.length && line[numberIndex] in '0'..'9') {
                    number = number * 10 + (line[numberIndex] - '0')
                    numberIndex++
                }
                if (number > 0) {
                    for (x in (index - 1).coerceAtLeast(0)..(numberIndex).coerceAtMost(line.length - 1)) {
                        for (y in (lineIndex - 1).coerceAtLeast(0)..(lineIndex + 1).coerceAtMost(input.size - 1)) {
                            if (input[y][x] == '*') {
                                gears.getOrPut(x to y) { mutableListOf() }.add(number)
                            }
                        }
                    }
                    index = numberIndex
                } else {
                    index++
                }
            }
        }
        return gears.values.filter { it.size == 2 }.sumOf { it[0] * it[1] }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    val result = part2(testInput)
    check(result == 467835) {
        "Got $result"
    }

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
