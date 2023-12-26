package com.example


class RepoLogic {
    val commits: MutableList<Commit> = mutableListOf()

    fun createCommit(tree: Tree, author: String, message: String) {
        commits.add(Commit(tree, author, message))
    }

    fun listCommits(): List<Commit> = commits.toList()

    fun searchCommits(query: String): List<Commit> =
        commits.filter { it.sha1.contains(query) || it.author.contains(query) || it.message.contains(query) }

}

