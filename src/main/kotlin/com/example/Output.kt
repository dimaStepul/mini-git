package com.example

interface Output  {
    fun printCommit(commit: Commit)
    fun printTreeCreationSuccess(name: String)
    fun printTreeAlreadyExists(name: String)

    fun printTreeSwitched(name: String)

    fun printTreeNotFound(name: String)

    fun printInvalidCommand()

    fun printExiting()
    fun printCurrentTree(currentTree: String)
}