fun main() {
    fun part1(input: List<String>): Int {
        val line = input.first()

        val checker = HashSet<Char>()
        for (i in 0 until (line.length - 3)) {
            for (j in 0..3) {
                checker.add(line[i + j])
            }

            if (checker.size == 4) {
                return i + 4
            }
            checker.clear()
        }
        return -1
    }

    fun part2(input: List<String>): Int {
        val line = input.first()

        val checker = HashSet<Char>()
        for (i in 0 until (line.length - 13)) {
            for (j in 0..13) {
                checker.add(line[i + j])
            }

            if (checker.size == 14) {
                return i + 14
            }
            checker.clear()
        }
        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day06_test01")
    val testInput2 = readInput("Day06_test02")
    val testInput3 = readInput("Day06_test03")
    val testInput4 = readInput("Day06_test04")

    check(part1(testInput1) == 5)
    check(part1(testInput2) == 6)
    check(part1(testInput3) == 10)
    check(part1(testInput4) == 11)

    check(part2(testInput1) == 23)
    check(part2(testInput2) == 23)
    check(part2(testInput3) == 29)
    check(part2(testInput4) == 26)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
