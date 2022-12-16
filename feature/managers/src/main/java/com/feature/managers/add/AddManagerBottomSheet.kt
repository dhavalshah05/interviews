package com.feature.managers.add

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
class AddManagerBottomSheet : TransparentBottomSheet() {

    companion object {
        const val TAG = "add_manager"

        fun newInstance(): AddManagerBottomSheet {
            return AddManagerBottomSheet()
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
                AddManagerScreen(
                    onAddClick = { validateAndContinue(it) }
                )
            }
        }
    }

    private fun validateAndContinue(name: String) {
        try {
            if (name.isBlank()) {
                throw Exception(getString(R.string.validation_manager_name_empty))
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
