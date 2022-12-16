package com.feature.interviews.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.data.interviews.InterviewResult
import com.service.presentation.R

internal enum class UiInterviewResult(
    @StringRes val textResId: Int,
    @ColorRes val bgColorResId: Int,
    @ColorRes val textColorResId: Int
) {
    PENDING(R.string.label_pending, R.color.orange, R.color.white),
    SELECTED(R.string.label_selected, R.color.green, R.color.white),
    REJECTED(R.string.label_rejected, R.color.red, R.color.white),
    HOLD(R.string.label_hold, R.color.greenYellow, R.color.white),
    CANCELLED(R.string.label_cancelled, R.color.grey, R.color.black);

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
