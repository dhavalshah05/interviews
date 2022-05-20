package com.template.app.ui.interviews.selectresult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.template.app.domain.interviews.models.InterviewResult
import com.template.app.ui.common.bottomsheet.TransparentBottomSheet
import com.template.app.ui.common.models.Selectable
import com.template.app.util.bundle.getParcelableValueOrError
import com.template.app.util.bundle.getStringValueOrError
import com.template.app.util.list.updateItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectResultBottomSheet : TransparentBottomSheet() {

    companion object {
        const val TAG = "select_result"
        private const val BUNDLE_REQUEST_KEY_SELECT_RESULT = "REQUEST_KEY_SELECT_RESULT"
        private const val BUNDLE_INTERVIEW_RESULT = "INTERVIEW_RESULT"

        fun newInstance(
            requestKeySelectResult: String,
            interviewResult: InterviewResult
        ): SelectResultBottomSheet {
            val bundle = Bundle()
            bundle.putString(BUNDLE_REQUEST_KEY_SELECT_RESULT, requestKeySelectResult)
            bundle.putParcelable(BUNDLE_INTERVIEW_RESULT, interviewResult)

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
        arguments.getParcelableValueOrError(BUNDLE_INTERVIEW_RESULT)
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