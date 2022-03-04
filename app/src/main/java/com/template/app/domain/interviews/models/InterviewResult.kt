package com.template.app.domain.interviews.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class InterviewResult : Parcelable {
    PENDING,
    SELECTED,
    REJECTED,
    HOLD,
    CANCELLED
}