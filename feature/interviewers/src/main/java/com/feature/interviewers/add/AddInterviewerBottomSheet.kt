package com.feature.interviewers.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.service.presentation.R
import com.service.presentation.bottomsheet.TransparentBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddInterviewerBottomSheet : TransparentBottomSheet() {

    companion object {
        const val TAG = "add_interview"

        fun newInstance(): AddInterviewerBottomSheet {
            return AddInterviewerBottomSheet()
        }
    }

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
                throw Exception(getString(R.string.validation_interviewer_name_empty))
            }

            setResult()
        } catch (e: Exception) {
            e.printStackTrace()
            val message = e.message ?: return
            // alert.showErrorMessage(message = message, dialog = dialog)
        }
    }

    private fun setResult() {
        dismiss()
    }
}
