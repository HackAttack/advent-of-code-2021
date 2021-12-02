fun main() {
    fun part1(input: List<String>): Int {
        var pos = 0
        var depth = 0
        for (line in input) {
            val (direction, length) = line.split(' ')
            val l = length.toInt()
            when (direction) {
                "forward" -> pos += l
                "up" -> depth -= l
                "down" -> depth += l
            }
        }
        return pos * depth
    }

    fun part2(input: List<String>): Int {
        var pos = 0
        var depth = 0
        var aim = 0
        for (line in input) {
            val (direction, length) = line.split(' ')
            val l = length.toInt()
            when (direction) {
                "down" -> aim += l
                "up" -> aim -= l
                "forward" -> {
                    pos += l
                    depth += l * aim
                }
            }
        }
        return pos * depth
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
