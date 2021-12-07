import kotlin.math.roundToInt

fun main() {
    fun part1(input: List<String>): Int {
        val slots = input.first().length
        val n = (0 until slots).map { i ->
            (input.sumOf { it[i].digitToInt() }.toDouble() / input.size).roundToInt()
        }.joinToString("").toInt(2)
        val mask = (1 shl slots) - 1
        return n * (n xor mask)
    }

    fun part2(input: List<String>): Int {
        val slots = input.first().length
        val least = input.toMutableList()
        val most = input.toMutableList()
        for (i in 0 until slots) {
            listOf(least, most).forEachIndexed { j, list ->
                if (list.size != 1) {
                    val l = list.groupBy { it[i] }.values.sortedWith(compareBy<List<String>> { it.size }
                        .thenBy { it.first()[i] })
                    list.retainAll(l[j])
                }
            }
        }
        return listOf(least, most).fold(1) { acc, list -> acc * list.single().toInt(2) }
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
