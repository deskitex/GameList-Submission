package com.example.gameengine

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val name: String,
    val dev: String,
    val date: String,
    val steam: String,
    val feature: String,
    val thumb: Int,
    val photo: Int,
    val desc: String
) : Parcelable
