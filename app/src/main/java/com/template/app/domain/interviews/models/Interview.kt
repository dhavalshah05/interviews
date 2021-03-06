package com.template.app.domain.interviews.models

import android.os.Parcelable
import com.template.app.domain.interviewers.models.Interviewer
import com.template.app.domain.managers.models.Manager
import kotlinx.parcelize.Parcelize

@Parcelize
data class Interview(
    val id: Long,
    val candidateName: String,
    val experience: String,
    val interviewDate: Long,
    val result: InterviewResult,
    val interviewer: Interviewer,
    val manager: Manager,
    val interviewComments: String,
    val practicalComments: String,
    val practicalLink: String
) : Parcelable
