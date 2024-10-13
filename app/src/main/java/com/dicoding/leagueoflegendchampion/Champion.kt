package com.dicoding.leagueoflegendchampion

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Champion(
    val name: String,
    val alias: String,
    val description: String,
    val role: String,
    val dificulty: String,
    val photo: String
) : Parcelable
