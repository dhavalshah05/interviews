package com.template.app.ui.interviewers.addinterviewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.template.app.R
import com.template.app.exception.ApplicationException
import com.template.app.ui.common.bottomsheet.TransparentBottomSheet
import com.template.app.util.alert.AlertNotification
import com.template.app.util.validator.Validator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddInterviewerBottomSheet : TransparentBottomSheet() {

    companion object {
        const val TAG = "add_interview"

        fun newInstance(): AddInterviewerBottomSheet {
            return AddInterviewerBottomSheet()
        }
    }

    @Inject
    lateinit var validator: Validator

    @Inject
    lateinit var alert: AlertNotification

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.Default)
            setContent {
                AddInterviewerScreen(
                    onAddClick = { validateAndContinue(it) }
                )
            }
        }
    }

    private fun validateAndContinue(name: String) {
        try {
            if (name.isBlank()) {
                throw ApplicationException(getString(R.string.validation_interviewer_name_empty))
            }

            setResult()
        } catch (e: ApplicationException) {
            e.printStackTrace()
            val message = e.message ?: return
            alert.showErrorMessage(message = message, dialog = dialog)
        }
    }

    private fun setResult() {
        dismiss()
    }
}