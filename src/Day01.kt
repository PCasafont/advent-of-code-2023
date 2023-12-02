import kotlin.streams.asSequence

fun main() {
    fun part1(input: List<String>): Int = input.sumOf { line ->
        (line.asSequence().first { it in ('0'..'9') } - '0') * 10 +
        (line.toCharArray().asSequence().last { it in ('0'..'9') } - '0')
    }

    fun part2(input: List<String>) = input.sumOf { line ->
        val numbers = mapOf("0" to 0, "1" to 1, "2" to 2, "3" to 3, "4" to 4, "5" to 5, "6" to 6, "7" to 7, "8" to 8, "9" to 9,
            "one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)
        val first = numbers.asSequence().map { (k, v) -> v to line.indexOf(k) }.filter { it.second >= 0 }.minBy { it.second }.first
        val last = numbers.asSequence().map { (k, v) -> v to line.lastIndexOf(k) }.maxBy { it.second }.first
        first * 10 + last
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
