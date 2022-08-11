package com.estebanrivera.samplemovies.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.estebanrivera.samplemovies.data.remote.CharacterDataSourceRemote
import java.math.BigInteger
import java.security.MessageDigest


inline fun <reified T : Activity> Context.startActivity(body: Intent.() -> Unit) {
    startActivity(Intent(this, T::class.java).apply(body))
}

fun CharacterDataSourceRemote.toHash(
    timeStamp: String,
    publicKey: String,
    privateKey: String
): String {
    val md = MessageDigest.getInstance("MD5")
    val bytes = md.digest("$timeStamp$privateKey$publicKey".toByteArray())
    val bigInteger = BigInteger(1, bytes)
    var md5 = bigInteger.toString(16)
    while (md5.length < 32) {
        md5 = "0$md5"
    }
    return md5
}

fun CharacterDataSourceRemote.getTimestamp(): String =
    System.currentTimeMillis().run { "$this" }
