package com.example

import java.security.MessageDigest


const val HASH_ALGO = "SHA-1"

fun calculateHash(input: String): String {
    val digest = MessageDigest.getInstance(HASH_ALGO)
    val hashBytes = digest.digest(input.toByteArray())
    return hashBytes.joinToString("") { "%02x".format(it) }
}