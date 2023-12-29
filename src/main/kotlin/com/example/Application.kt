import com.example.*

fun main() {
    val consoleOutput = ConsoleOutput()
    val jetRepo = RepoImpl(ConsoleOutput())

    jetRepo.createTree("master")

    while (true) {
        print("Enter command (jet list, jet commit, jet search, jet branch, jet switch, jet checkout, exit): ")
        val command = readlnOrNull()?.trim() ?: ""

        when (command) {
            "jet list" -> {
                jetRepo.listCommits()
            }
            "jet commit" -> {
                print("Enter author: ")
                val author = readlnOrNull()?.trim() ?: ""
                print("Enter commit message: ")
                val message = readlnOrNull()?.trim() ?: ""
                val tree = jetRepo.currentTree
                if (tree != null) {
                    jetRepo.createCommit(tree, author, message)
                } else {
                    println("Please, create branch")
                }
                println("Commit created success")
            }
            "jet search" -> {
                print("Enter commit hash or metadata (author/message): ")
                val input = readlnOrNull()?.trim() ?: ""

                val commits = jetRepo.searchCommits(input)

                if (commits.isNotEmpty()) {
                    commits.forEach { commit ->
                        println("Commit found:")
                        consoleOutput.printCommit(commit)
                    }
                } else {
                    println("Commit not found")
                }
            }
            "jet branch" -> {
                val currentBranch = jetRepo.currentTree
                if (currentBranch != null) {
                    consoleOutput.printCurrentTree(currentBranch.sha1)
                } else {
                    println("No current branch selected.")
                }
            }
            "jet switch" -> {
                print("Enter branch name to switch to: ")
                val branchName = readlnOrNull()?.trim() ?: ""
                jetRepo.switchTree(branchName)
            }
            "exit" -> {
                consoleOutput.printExiting()
                return
            }
            "jet checkout" -> {
                print("Enter branch name to checkout: ")
                val branchName = readlnOrNull()?.trim() ?: ""
                jetRepo.checkoutTree(branchName)
            }
            else -> {
                consoleOutput.printInvalidCommand()
            }
        }
    }
}
