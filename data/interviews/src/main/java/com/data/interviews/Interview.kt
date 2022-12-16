package com.data.interviews

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Interview constructor(
    val id: Long,
    val candidateName: String,
    val experience: String,
    val interviewDate: Long,
    val result: InterviewResult,
    val interviewerId: Long,
    val interviewerName: String,
    val managerId: Long,
    val managerName: String,
    val interviewComments: String,
    val practicalComments: String,
    val practicalLink: String
) : Parcelable
