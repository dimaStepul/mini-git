package com.example


data class Blob(val data: String) {
    val sha1: String = calculateSHA1(data)
}