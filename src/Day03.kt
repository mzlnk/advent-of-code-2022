fun main() {
    fun part1(input: List<String>): Int {
        val letterToPriority: (Char) -> Int =
            { letter -> (if (letter in 'a'..'z') (letter.code - 97) else (letter.code - 39)) + 1 }

        var sum = 0
        for ((index: Int, line: String) in input.withIndex()) {
            val pack1 = HashSet<Char>()

            for (i in 0 until line.length / 2) {
                pack1.add(line[i])
            }

            for (i in (line.length / 2) until line.length) {
                if (pack1.contains(line[i])) {
                    sum += letterToPriority(line[i])
                    break;
                }
            }
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        val letterToPriority: (Char) -> Int =
            { letter -> (if (letter in 'a'..'z') (letter.code - 97) else (letter.code - 39)) + 1 }

        var sum = 0
        for (i in 0..(input.size - 3) step 3) {
            val itemTypes = HashMap<Char, Int>()

            for (j in 0..2) {
                val computedLetters = HashSet<Char>()
                input[i + j].toCharArray().forEach {
                    itemTypes.compute(it) { k, v ->
                        if (!computedLetters.contains(k)) {
                            computedLetters.add(k)
                            ((v ?: 0) + 1)
                        } else v
                    }
                }
            }

            val commonLetter = itemTypes.filterValues { it == 3 }.toList().find { it.second == 3 }?.first!!
            println("Pair ${i / 3}: $commonLetter")

            sum += letterToPriority(commonLetter)
        }

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
