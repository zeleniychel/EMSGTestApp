package com.example.emsgtestapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Long,
    val login: String
) : Parcelable
