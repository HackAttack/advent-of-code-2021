data class Square(val value: Int, var marked: Boolean = false)

class Board(l: List<List<String>>) {
    val items: List<List<Square>> = l.map { it.map { Square(it.toInt()) } }

    fun mark(x: Int) {
        for (i in items.indices) {
            for (j in items[0].indices) {
                if (items[i][j].value == x) {
                    items[i][j].marked = true
                }
            }
        }
    }

    fun won() = items.indices.any { i -> items[0].indices.all { j -> items[i][j].marked } }
            || items[0].indices.any { j -> items.indices.all { i -> items[i][j].marked } }
}

const val BOARD_SIZE = 5

fun main() {
    fun readBoard(input: List<String>, pos: Int) =
        Board(input.subList(pos, pos + BOARD_SIZE).map { it.trim().split(" +".toPattern()) })

    fun part1(input: List<String>): Int? {
        val moves = input[0].split(',').map { it.toInt() }
        val boards = (2 .. input.size - BOARD_SIZE step BOARD_SIZE + 1).map { readBoard(input, it) }
        for (move in moves) {
            boards.forEach { it.mark(move) }
            val winner = boards.singleOrNull { it.won() }
            if (winner != null) {
                return move * winner.items.flatten().filterNot { it.marked }.sumOf { it.value }
            }
        }
        return null
    }

    fun part2(input: List<String>): Int? {
        val moves = input[0].split(',').map { it.toInt() }
        val boards = (2 .. input.size - BOARD_SIZE step BOARD_SIZE + 1).map { readBoard(input, it) }
            .toMutableList()
        for (move in moves) {
            boards.forEach { it.mark(move) }
            if (boards.singleOrNull()?.won() == true) {
                return move * boards.single().items.flatten().filterNot { it.marked }.sumOf { it.value }
            }
            boards.removeAll { it.won() }
        }
        return null
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
