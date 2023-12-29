package com.example

import kotlin.test.Test
import kotlin.test.assertEquals

class RepoLogicTest {


    @Test
    fun `commit creation`() {
        val output = ConsoleOutput()
        val repo = RepoLogic(output)

        val blob = Blob("Hello world")
        val tree = Tree(listOf(TreeNode(blob.data)))
        repo.createCommit(tree, "Jonny boy", "Initial commit")

        val allCommits = repo.commitsToList()

        assertEquals(1, allCommits.size)
        assertEquals("Jonny boy", allCommits[0].author)
        assertEquals("Initial commit", allCommits[0].message)
    }

    @Test
    fun `list all commits`() {
        val output = ConsoleOutput()
        val repo = RepoLogic(output)
        val blob1 = Blob("Hello world")
        val blob2 = Blob("simple blob")
        val tree = Tree(listOf(TreeNode(blob1.data), TreeNode(blob2.data)))
        repo.createCommit(tree, "Jonny boy", "Initial commit")

        val allCommits = repo.commitsToList()

        assertEquals(1, allCommits.size)
        assertEquals("Jonny boy", allCommits[0].author)
        assertEquals("Initial commit", allCommits[0].message)
    }

    @Test
    fun `searching in commits`() {
        val output = ConsoleOutput()
        val repo = RepoLogic(output)
        val blob1 = Blob("Hello world!")
        val blob2 = Blob("simple blob.")
        val tree = Tree(listOf(TreeNode(blob1.data), TreeNode(blob2.data)))
        repo.createCommit(tree, "Jonny boy", "Initial commit")
        val searchResult = repo.searchCommits("Jonny boy")

        assertEquals(1, searchResult.size)
        assertEquals("Jonny boy", searchResult[0].author)
        assertEquals("Initial commit", searchResult[0].message)
    }
}