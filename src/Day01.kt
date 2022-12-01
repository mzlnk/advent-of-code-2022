fun main() {
    fun part1(input: List<String>): Int {
        var max = 0
        var current = 0

        for(line: String in input) {
            if(line.isEmpty()) {
                max = Math.max(max, current)
                current = 0
                continue
            }
            current += line.toInt()
        }
        max = Math.max(max, current)

        return max
    }

    fun part2(input: List<String>): Int {
        val calories = ArrayList<Int>()

        var current = 0
        for(line: String in input) {
            if(line.isEmpty()) {
                calories.add(current)
                current = 0
                continue
            }
            current += line.toInt()
        }
        calories.add(current)

        return calories.sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24_000)
    check(part2(testInput) == 45_000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
