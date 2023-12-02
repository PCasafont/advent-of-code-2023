
fun main() {
    fun part1(input: List<String>): Int = input.sumOf { line ->
        val gameId = line.substringAfter("Game ").substringBefore(":").toInt()
        line.substringAfter(":").split(";").forEach { setString->
            val set = setString.trim().split(",").map {
                val split = it.trim().split(" ")
                split[0].toInt() to split[1]
            }
            val colors = mutableMapOf<String, Int>()
            set.forEach { (count, color) ->
                colors[color] = count + (colors[color] ?: 0)
            }
            for ((color, count) in colors) {
                when (color) {
                    "red" -> if (count > 12) return@sumOf 0
                    "green" -> if (count > 13) return@sumOf 0
                    "blue" -> if (count > 14) return@sumOf 0
                    else -> return@sumOf 0
                }
            }
        }
        gameId
    }

    fun part2(input: List<String>): Int = input.sumOf { line ->
        val gameId = line.substringAfter("Game ").substringBefore(":").toInt()
        var red = 0
        var green = 0
        var blue = 0
        line.substringAfter(":").split(";").forEach { setString->
            val set = setString.trim().split(",").map {
                val split = it.trim().split(" ")
                split[0].toInt() to split[1]
            }
            val colors = mutableMapOf<String, Int>()
            set.forEach { (count, color) ->
                colors[color] = count + (colors[color] ?: 0)
            }
            for ((color, count) in colors) {
                when (color) {
                    "red" -> if (count > red) red = count
                    "green" -> if (count > green) green = count
                    "blue" -> if (count > blue) blue = count
                }
            }
        }
        red * green * blue
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
