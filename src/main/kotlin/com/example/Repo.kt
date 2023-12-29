package com.example

interface Repo {

    fun createCommit(tree: Tree, author: String, message: String)
    fun searchCommits(query: String): List<Commit>

    fun createTree(name: String = "")

    fun switchTree(name: String)

    fun checkoutTree(treeName: String)

    fun listCommits()
}