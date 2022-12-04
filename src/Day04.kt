fun main() {
    fun part1(input: List<String>): Int {
        var pairs = 0
        for (line: String in input) {
            val rawData = line.split(',').flatMap { it.split('-') }.map { it.toInt() }

            val firstPair = Pair(rawData[0], rawData[1])
            val secondPair = Pair(rawData[2], rawData[3])

            val isFirstPairOverlapping = firstPair.first <= secondPair.first && firstPair.second >= secondPair.second
            val isSecondPairOverlapping = secondPair.first <= firstPair.first && secondPair.second >= firstPair.second

            val isOverlapping = isFirstPairOverlapping || isSecondPairOverlapping
            if(isOverlapping) {
                pairs++
            }
        }
        return pairs
    }

    fun part2(input: List<String>): Int {
        var pairs = 0
        for(line: String in input) {
            val rawData = line.split(',').flatMap { it.split('-') }.map { it.toInt() }

            val firstPair = Pair(rawData[0], rawData[1])
            val secondPair = Pair(rawData[2], rawData[3])

            if(!(firstPair.second < secondPair.first || secondPair.second < firstPair.first)) {
                pairs++
            }
        }
        return pairs
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
