import java.lang.StringBuilder
import java.util.Stack

fun main() {
    fun part1(input: List<String>): String {
        val stackLines = input.takeWhile { it[1] != '1' }.reversed()
        val numbersLine = input[stackLines.size]
        val instructionLines = input.subList(stackLines.size + 2, input.size)

        val stacksSize = numbersLine.split(' ').last().toInt()

        val stacks = ArrayList<Stack<Char>>()
        for (idx: Int in 0 until stacksSize) {
            stacks.add(Stack())
        }

        for (stackLine: String in stackLines) {
            for ((stackIdx, offset) in (0..stacks.size).zip(1 until stackLine.length step 4)) {
                val letter = stackLine[offset]
                if (letter.isLetter()) {
                    stacks[stackIdx].push(stackLine[offset])
                }
            }
        }

        for (instruction: String in instructionLines) {
            val pattern = Regex("move (\\d+) from (\\d+) to (\\d+)")
            val (amount, from, to) = pattern.find(instruction)!!.destructured

            for (i: Int in 0 until amount.toInt()) {
                val popped = stacks[from.toInt() - 1].pop()
                stacks[to.toInt() - 1].push(popped)
            }
        }

        return stacks.map { it.pop() }.joinTo(buffer = StringBuilder(), separator = "").toString()
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == 0)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
