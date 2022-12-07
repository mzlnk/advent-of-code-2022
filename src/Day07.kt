fun main() {
    fun part1(input: List<String>): Int {
        val root = Directory(name = "/")

        var current: Directory = root

        var lineIdx = 0
        while (lineIdx < input.size) {
            val line = input[lineIdx++]

            val pattern = Regex("\\$ (cd|ls) ?(.+)?")
            val (command, option) = pattern.find(line)!!.destructured

            when (command) {
                "cd" -> {
                    current = when (option) {
                        ".." -> current.switchToParent()
                        "/" -> current.switchToRoot()
                        else -> current.switchToChild(option)
                    }
                }

                "ls" -> {
                    while (lineIdx < input.size && !input[lineIdx].startsWith("$")) {
                        val lsData = input[lineIdx].split(" ")
                        when (lsData[0]) {
                            "dir" -> current.directories[lsData[1]] =
                                Directory(root = root, parent = current, name = lsData[1])

                            else -> current.files.add(File(lsData[1], lsData[0].toInt()))
                        }
                        lineIdx++
                    }
                }
            }
        }

        return root.listDirectoriesWithSize()
            .filter { it.second <= 100000 }
            .sumOf { it.second }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")

    check(part1(testInput) == 95437)
    check(part2(testInput) == 0)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}

data class File(val name: String, val size: Int)

data class Directory(
    val root: Directory? = null,
    var parent: Directory? = null,
    val name: String,
    var files: MutableList<File> = mutableListOf(),
    var directories: MutableMap<String, Directory> = mutableMapOf()
) {

    private var size: Int? = null

    fun switchToRoot(): Directory {
        return root ?: this
    }

    fun switchToParent(): Directory {
        return parent ?: this
    }

    fun switchToChild(name: String): Directory {
        return directories[name]!!
    }

    fun listDirectoriesWithSize(): List<Pair<String, Int>> {
        val children = directories.values.flatMap { it.listDirectoriesWithSize() }
        val self = Pair(name, readOrCalculateSize())

        return children + self
    }

    private fun readOrCalculateSize(): Int {
        this.size = this.size ?: calculateSize()
        return this.size!!
    }

    private fun calculateSize(): Int {
        val filesSize = files.sumOf { it.size }
        val directoriesSize = directories.values.sumOf { it.readOrCalculateSize() }

        return filesSize + directoriesSize
    }

    override fun toString(): String {
        return "Directory(root=${root?.name}, parent=${parent?.name}, name=$name)"
    }
}
