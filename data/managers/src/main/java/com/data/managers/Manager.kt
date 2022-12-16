package com.data.managers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Manager(
    val id: Long,
    val name: String
) : Parcelable
