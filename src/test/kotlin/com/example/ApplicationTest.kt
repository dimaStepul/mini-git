package com.example

import kotlin.test.Test
import kotlin.test.assertEquals

class RepoLogicTest {


    @Test
    fun `commit creation`() {
        val repo = RepoLogic()
        val blob = Blob("Hello world")
        val tree = Tree(listOf(TreeNode("file1.txt", blob.hash)))
        repo.createCommit(tree, "Jonny boy", "Initial commit")

        val allCommits = repo.listCommits()

        assertEquals(1, allCommits.size)
        assertEquals("Jonny boy", allCommits[0].author)
        assertEquals("Initial commit", allCommits[0].message)
    }

    @Test
    fun `list all commits`() {
        val repo = RepoLogic()
        val blob1 = Blob("Hello world")
        val blob2 = Blob("simple blob")
        val tree = Tree(listOf(TreeNode("file1.txt", blob1.hash), TreeNode("file2.txt", blob2.hash)))
        repo.createCommit(tree, "Jonny boy", "Initial commit")

        val allCommits = repo.listCommits()

        assertEquals(1, allCommits.size)
        assertEquals("Jonny boy", allCommits[0].author)
        assertEquals("Initial commit", allCommits[0].message)
    }

    @Test
    fun `searching in commits`() {
        val repo = RepoLogic()
        val blob1 = Blob("Hello world!")
        val blob2 = Blob("simple blob.")
        val tree = Tree(listOf(TreeNode("file1.txt", blob1.hash), TreeNode("file2.txt", blob2.hash)))
        repo.createCommit(tree, "Jonny boy", "Initial commit")
        val searchResult = repo.searchCommits("Jonny boy")

        assertEquals(1, searchResult.size)
        assertEquals("Jonny boy", searchResult[0].author)
        assertEquals("Initial commit", searchResult[0].message)
    }
}