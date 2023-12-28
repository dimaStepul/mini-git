package com.example


data class Blob(val data: String) {
    val hash: String = calculateHash(data)
}