package com.template.app.ui.interviews

import androidx.annotation.StringRes
import com.template.app.R
import com.template.app.domain.interviews.models.InterviewResult

enum class UiInterviewResult(@StringRes val textResId: Int) {
    PENDING(R.string.label_pending),
    SELECTED(R.string.label_selected),
    REJECTED(R.string.label_rejected),
    HOLD(R.string.label_hold),
    CANCELLED(R.string.label_cancelled);

    companion object {
        fun from(interviewResult: InterviewResult): UiInterviewResult {
            return when (interviewResult) {
                InterviewResult.PENDING -> PENDING
                InterviewResult.SELECTED -> SELECTED
                InterviewResult.REJECTED -> REJECTED
                InterviewResult.HOLD -> HOLD
                InterviewResult.CANCELLED -> CANCELLED
            }
        }
    }
}