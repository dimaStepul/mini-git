package com.example


data class TreeNode(val name: String)
data class Tree(val entries: List<TreeNode>) {
    val sha1: String = calculateHash(entries.joinToString { it.name })
}
