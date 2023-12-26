package com.example

import java.util.Date

data class Commit(
    val tree: Tree,
    val author: String,
    val message: String,
    val commitTime: Date = Date(),
    val sha1: String = calculateSHA1(tree.sha1 + author + message + commitTime)
)
