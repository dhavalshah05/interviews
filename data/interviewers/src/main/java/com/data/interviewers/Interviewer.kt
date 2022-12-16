package com.data.interviewers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Interviewer(
    val id: Long,
    val name: String
) : Parcelable
