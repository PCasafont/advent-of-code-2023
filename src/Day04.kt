private fun Int.pow(other: Int): Int {
    var result = 1
    repeat(other) {
        result *= this
    }
    return result
}

fun main() {
    fun part1(input: List<String>): Int = input.sumOf { line ->
        val card = line.substringAfter(":").split("|")
        val winningNumbers = card[0].split(" ").mapNotNull { it.toIntOrNull() }
        val numbers = card[1].split(" ").mapNotNull { it.toIntOrNull() }
        val wins = winningNumbers.count { it in numbers }
        if (wins == 0) 0 else 2.pow(wins - 1)
    }

    fun part2(input: List<String>): Int {
        val wins = input.map { line ->
            val card = line.substringAfter(":").split("|")
            val winningNumbers = card[0].split(" ").mapNotNull { it.toIntOrNull() }
            val numbers = card[1].split(" ").mapNotNull { it.toIntOrNull() }
            winningNumbers.count { it in numbers }
        }
        val copies = Array(wins.size) { 1 }
        for (i in wins.indices) {
            val currentCopies = copies[i]
            val currentWins = wins[i]
            repeat(currentWins) {
                copies[i + 1 + it] += currentCopies
            }
        }
        return copies.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part2(testInput) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
