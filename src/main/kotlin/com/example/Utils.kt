package com.example

import java.security.MessageDigest


const val HASH_ALGO = "SHA-1"

fun calculateHash(input: String): String {
    val digest = MessageDigest.getInstance(HASH_ALGO)
    val hashBytes = digest.digest(input.toByteArray())
    return hashBytes.joinToString("") { "%02x".format(it) }
}


class ConsoleOutput : Output {
    override  fun printCommit(commit: Commit) {
        println("Author: ${commit.author}")
        println("Message: ${commit.message}")
        println("Commit Time: ${commit.commitTime}")
        println("$HASH_ALGO: ${commit.hash}")
    }



    override fun printTreeCreationSuccess(name: String) {
        println("Tree $name created successfully with an initial commit.")
    }

    override fun printTreeAlreadyExists(name: String) {
        println("Tree with name $name already exists.")
    }

    override fun printTreeSwitched(name: String) {
        println("Switched to Tree $name.")
    }

    override fun printTreeNotFound(name: String) {
        println("Tree $name not found.")
    }

    override fun printInvalidCommand() {
        println("Invalid command. Please enter a valid command.")
    }

    override fun printExiting() {
        println("Exiting.")
    }
    override fun printCurrentTree(currentTree: String) {
        println("Current Tree hash: $currentTree")
    }

}
