fun main() {
    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day06_test01")
    val testInput2 = readInput("Day06_test02")
    val testInput3 = readInput("Day06_test03")
    val testInput4 = readInput("Day06_test04")

    check(part1(testInput1) == 0)
    check(part1(testInput2) == 0)
    check(part1(testInput3) == 0)
    check(part1(testInput4) == 0)

    check(part2(testInput1) == 0)
    check(part2(testInput2) == 0)
    check(part2(testInput3) == 0)
    check(part2(testInput4) == 0)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
