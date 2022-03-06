package com.template.app.ui.deletemanager

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.template.app.R
import com.template.app.util.bundle.getStringValueOrError

class DeleteManagerConfirmationDialog : DialogFragment() {

    companion object {
        const val TAG = "delete_manager_confirm"
        private const val BUNDLE_REQUEST_KEY_DELETE_MANAGER_CONFIRM = "REQUEST_KEY_DELETE_CONFIRM"

        fun newInstance(requestKeyDeleteManagerConfirm: String): DeleteManagerConfirmationDialog {
            return DeleteManagerConfirmationDialog().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_REQUEST_KEY_DELETE_MANAGER_CONFIRM, requestKeyDeleteManagerConfirm)
                }
            }
        }
    }

    private lateinit var requestKeyDeleteConfirm: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestKeyDeleteConfirm = arguments.getStringValueOrError(BUNDLE_REQUEST_KEY_DELETE_MANAGER_CONFIRM)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext(), R.style.AlertDialog)
            .setTitle(getString(R.string.dialog_delete_manager_title))
            .setMessage(getString(R.string.dialog_delete_manager_message))
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