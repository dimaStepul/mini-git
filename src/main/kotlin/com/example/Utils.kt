package com.example

import java.security.MessageDigest

fun calculateSHA1(input: String): String {
    val digest = MessageDigest.getInstance("SHA-1")
    val hashBytes = digest.digest(input.toByteArray())
    return hashBytes.joinToString("") { "%02x".format(it) }
}