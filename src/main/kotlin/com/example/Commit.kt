package com.example

import java.util.Date

data class Commit(
    val tree: Tree,
    val author: String,
    val message: String,
    val commitTime: Date = Date(),
    val hash: String = calculateHash(tree.sha1 + author + message + commitTime)
)
