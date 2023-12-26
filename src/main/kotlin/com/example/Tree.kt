package com.example


data class TreeNode(val name: String, val sha1: String)
data class Tree(val entries: List<TreeNode>) {
    val sha1: String = calculateSHA1(entries.joinToString { it.name + it.sha1 })
}
