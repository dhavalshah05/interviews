package com.feature.interviewers.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.data.interviewers.Interviewer
import com.data.interviewers.PreviewData
import com.service.presentation.bottomsheet.TransparentBottomSheet
import com.service.presentation.model.Selectable
import com.service.presentation.utils.bundle.getLongValueOrError
import com.service.presentation.utils.bundle.getStringValueOrError
import com.service.presentation.utils.list.updateItem

class SelectInterviewerBottomSheet : TransparentBottomSheet() {

    companion object {
        const val TAG = "select_interviewer"
        private const val BUNDLE_REQUEST_KEY_SELECT_INTERVIEWER = "REQUEST_KEY_SELECT_INTERVIEWER"
        private const val BUNDLE_INTERVIEWER_ID = "INTERVIEWER_ID"

        fun newInstance(requestKeySelectInterviewer: String, interviewerId: Long?): SelectInterviewerBottomSheet {
            val bundle = Bundle()
            bundle.putString(BUNDLE_REQUEST_KEY_SELECT_INTERVIEWER, requestKeySelectInterviewer)
            bundle.putLong(BUNDLE_INTERVIEWER_ID, interviewerId ?: 0L)

            val dialog = SelectInterviewerBottomSheet()
            dialog.arguments = bundle
            return dialog
        }
    }

    private val requestKeySelectInterviewer: String by lazy {
        arguments.getStringValueOrError(BUNDLE_REQUEST_KEY_SELECT_INTERVIEWER)
    }

    private val items = mutableStateOf(PreviewData.getInterviewers().map { Selectable(item = it, selected = false) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val selectedInterviewerId = arguments.getLongValueOrError(BUNDLE_INTERVIEWER_ID)
        val selectableItem = items.value.find { it.item.id == selectedInterviewerId }
        if (selectableItem != null) {
            items.value = items.value.updateItem(selectableItem.copy(selected = true)) {
                it.item.id == selectableItem.item.id
            }
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
                SelectInterviewerScreen(
                    items = items.value,
                    onItemSelect = { notifyParent(it.item) }
                )
            }
        }
    }

    private fun notifyParent(interviewer: Interviewer) {
        val bundle = Bundle()
        bundle.putParcelable("interviewer", interviewer)
        parentFragmentManager.setFragmentResult(requestKeySelectInterviewer, bundle)
        dismiss()
    }
}
