fun main() {
    fun part1(input: List<String>) = input.map { it.toInt() }
        .zipWithNext { a, b -> if (a < b) 1 else 0 }.sum()

    fun part2(input: List<String>) = input.map { it.toInt() }
        .windowed(3, transform = { it.sum() })
        .zipWithNext { a, b -> if (a < b) 1 else 0 }.sum()

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
