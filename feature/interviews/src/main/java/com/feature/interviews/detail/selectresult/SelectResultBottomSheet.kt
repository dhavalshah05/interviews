package com.feature.interviews.detail.selectresult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.data.interviews.InterviewResult
import com.service.presentation.bottomsheet.TransparentBottomSheet
import com.service.presentation.model.Selectable
import com.service.presentation.utils.bundle.getStringValueOrError
import com.service.presentation.utils.list.updateItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectResultBottomSheet : TransparentBottomSheet() {

    companion object {
        const val TAG = "select_result"
        private const val BUNDLE_REQUEST_KEY_SELECT_RESULT = "REQUEST_KEY_SELECT_RESULT"
        private const val BUNDLE_INTERVIEW_RESULT = "INTERVIEW_RESULT"

        fun newInstance(
            requestKeySelectResult: String,
            interviewResult: String
        ): SelectResultBottomSheet {
            val bundle = Bundle()
            bundle.putString(BUNDLE_REQUEST_KEY_SELECT_RESULT, requestKeySelectResult)
            bundle.putString(BUNDLE_INTERVIEW_RESULT, interviewResult)

            val dialog = SelectResultBottomSheet()
            dialog.arguments = bundle
            return dialog
        }
    }

    private val items = mutableStateOf(InterviewResult.values().toList().map { Selectable(it, false) })

    private val requestKeySelectResult: String by lazy {
        arguments.getStringValueOrError(BUNDLE_REQUEST_KEY_SELECT_RESULT)
    }

    private val interviewResult: InterviewResult by lazy {
        val string = arguments.getStringValueOrError(BUNDLE_INTERVIEW_RESULT)
        InterviewResult.values().find { it.name == string }
            ?: throw Exception("InterviewResult not found for given $string")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items.value = items.value.updateItem(Selectable(item = interviewResult, selected = true)) { it.item.name == interviewResult.name }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.Default)
            setContent {
                SelectResultScreen(
                    items = items.value,
                    onItemSelect = {
                        notifyParent(it.item)
                    }
                )
            }
        }
    }

    private fun notifyParent(result: InterviewResult) {
        val bundle = Bundle()
        bundle.putParcelable("interview_result", result)
        parentFragmentManager.setFragmentResult(requestKeySelectResult, bundle)
        dismiss()
    }
}
