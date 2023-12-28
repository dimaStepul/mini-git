import com.example.*


fun createSampleTree(factory: TreeFactory): Tree {
    val node1 = TreeNode("file1.txt", calculateHash("hash1"))
    val node2 = TreeNode("file2.txt", calculateHash("hash2"))
    val node3 = TreeNode("file3.txt", calculateHash("hash3"))

    return factory.createTree(listOf(node1, node2, node3))
}


fun main() {
    val jetRepo = RepoLogic()

    println("Welcome to the Jet Console App!")
    val treeFactory = TreeFactory()
    val tree = createSampleTree(treeFactory)

    while (true) {
        print("Enter command (jet list, jet commit, jet search, exit): ")
        val command = readlnOrNull()?.trim() ?: ""

        when (command) {
            "jet list" -> {
                val commits = jetRepo.listCommits()
                if (commits.isEmpty()) {
                    println("No commits found.")
                } else {
                    println("Commits:")
                    commits.forEachIndexed { index, commit ->
                        println("$index: ${commit.author} - ${commit.message} - ${commit.commitTime}")
                    }
                }
            }
            "jet commit" -> {
                print("Enter author: ")
                val author = readLine()?.trim() ?: ""
                print("Enter commit message: ")
                val message = readLine()?.trim() ?: ""
                jetRepo.createCommit(tree, author, message)
                println("Commit created success")
            }
            "jet search" -> {
                print("Enter commit hash or metadata (author/message): ")
                val input = readLine()?.trim() ?: ""

                val commits = jetRepo.searchCommits(input)

                if (commits.isNotEmpty()) {
                    commits.forEach { commit ->
                        println("Commit found:")
                        println("Author: ${commit.author}")
                        println("Message: ${commit.message}")
                        println("Commit Time: ${commit.commitTime}")
                    }
                } else {
                    println("Commit not found")
                }
            }
            "exit" -> {
                println("Exiting")
                return
            }
            else -> {
                println("Invalid command. Please enter a valid command")
            }
        }
    }
}
