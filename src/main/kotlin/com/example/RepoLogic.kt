package com.example

class RepoLogic(private val output: ConsoleOutput) {
    private val commits: MutableList<Commit> = mutableListOf()

    private val trees: MutableMap<String, Tree> = mutableMapOf()
    var currentTree: Tree? = null
        private set


    fun createCommit(tree: Tree, author: String, message: String) {
        commits.add(Commit(tree, author, message))
    }

    fun commitsToList() = commits.toList()
    fun listCommits() {
        commits.forEachIndexed { _, commit ->
            output.printCommit(commit)
        }
    }

    fun searchCommits(query: String): List<Commit> =
        commits.filter { it.hash.contains(query) || it.author.contains(query) || it.message.contains(query) }

    fun createTree(name: String = "") {
        if (trees.containsKey(name)) {
            output.printTreeAlreadyExists(name)
        } else {
            val initialCommit = Commit(Tree(listOf(TreeNode(name))), "System", "Initial commit")
            trees[name] = initialCommit.tree
            currentTree = trees[name]
            output.printTreeCreationSuccess(name)
        }
    }


    fun switchTree(name: String) {
        if (trees.containsKey(name)) {
            currentTree = trees[name]
            output.printTreeSwitched(name)
        } else {
            output.printTreeNotFound(name)
        }
    }

    fun checkoutTree(treeName: String) {
        createTree(treeName)
        currentTree = trees[treeName]
    }
}

