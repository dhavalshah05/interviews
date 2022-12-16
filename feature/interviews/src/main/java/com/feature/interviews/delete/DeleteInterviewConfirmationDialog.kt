package com.feature.interviews.delete

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.service.presentation.R
import com.service.presentation.utils.bundle.getStringValueOrError

class DeleteInterviewConfirmationDialog : DialogFragment() {

    companion object {
        const val TAG = "delete_interview_confirm"
        private const val BUNDLE_REQUEST_KEY_DELETE_INTERVIEW_CONFIRM = "REQUEST_KEY_DELETE_CONFIRM"

        fun newInstance(requestKeyDeleteInterviewConfirm: String): DeleteInterviewConfirmationDialog {
            return DeleteInterviewConfirmationDialog().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_REQUEST_KEY_DELETE_INTERVIEW_CONFIRM, requestKeyDeleteInterviewConfirm)
                }
            }
        }
    }

    private lateinit var requestKeyDeleteConfirm: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestKeyDeleteConfirm = arguments.getStringValueOrError(BUNDLE_REQUEST_KEY_DELETE_INTERVIEW_CONFIRM)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext(), R.style.AlertDialog)
            .setTitle(getString(R.string.dialog_delete_interview_title))
            .setMessage(getString(R.string.dialog_delete_interview_message))
            .setPositiveButton(getString(R.string.button_yes)) { _, _ -> setResult() }
            .setNegativeButton(getString(R.string.button_no)) { _, _ -> }
            .setCancelable(true)
            .create()
    }

    private fun setResult() {
        parentFragmentManager.setFragmentResult(requestKeyDeleteConfirm, Bundle())
        dismiss()
    }

}