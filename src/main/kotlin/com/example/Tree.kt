package com.example


data class TreeNode(val name: String, val sha1: String)
data class Tree(val entries: List<TreeNode>) {
    val sha1: String = calculateHash(entries.joinToString { it.name + it.sha1 })
}



class TreeFactory {
    fun createTree(nodes: List<TreeNode>): Tree {
        return Tree(nodes)
    }
}