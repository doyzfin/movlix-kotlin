package com.dicoding.movflix

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val name: String,
    val year: String,
    val genre: String,
    val photo: String,
    val plot: String
) : Parcelable
