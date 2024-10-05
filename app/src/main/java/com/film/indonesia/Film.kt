package com.film.indonesia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val title: String,
    val description: String,
    val photo: String,
    val genre: String,
    val director: String,
    val year: String,
    val viewers: Int
) : Parcelable
