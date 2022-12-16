package com.feature.interviews.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.data.interviews.Interview
import com.data.interviews.InterviewResult
import com.data.interviews.PreviewData
import com.service.navigation.Navigator
import com.service.presentation.utils.bundle.getLongValueOrError
import com.service.presentation.utils.bundle.getParcelableValueOrError
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InterviewDetailsFragment : Fragment() {

    companion object {
        private const val REQUEST_KEY_EDIT_INTERVIEW = "edit_interview"
        private const val REQUEST_KEY_SELECT_RESULT = "select_result"
        private const val REQUEST_KEY_DELETE_INTERVIEW_CONFIRM = "delete_interview_confirm"
        private const val BUNDLE_INTERVIEW_ID = "INTERVIEW_ID"

        fun createBundle(interviewId: Long): Bundle {
            return Bundle().apply {
                putLong(BUNDLE_INTERVIEW_ID, interviewId)
            }
        }
    }

    @Inject
    lateinit var navigator: Navigator

    private lateinit var interview: MutableState<Interview>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val interviewId = arguments.getLongValueOrError(BUNDLE_INTERVIEW_ID)
        interview = mutableStateOf(PreviewData.getInterviews().first())
        parentFragmentManager.setFragmentResultListener(REQUEST_KEY_SELECT_RESULT, this) { _, result ->
            val interviewResult: InterviewResult = result.getParcelableValueOrError("interview_result")
            interview.value = interview.value.copy(result = interviewResult)
        }
        parentFragmentManager.setFragmentResultListener(REQUEST_KEY_DELETE_INTERVIEW_CONFIRM, this) { _, _ ->
            navigator.goBack()
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
                InterviewDetailScreen(
                    interview = interview.value,
                    onBackClick = onBackClick,
                    onEditInterviewClick = onEditInterviewClick,
                    onDeleteInterviewClick = onDeleteInterviewClick,
                    onEditInterviewResultClick = onEditInterviewResultClick,
                )
            }
        }
    }

    private val onBackClick: () -> Unit = {
        navigator.goBack()
    }

    private val onEditInterviewClick: () -> Unit = {
        navigator.openEditInterviewScreen(REQUEST_KEY_EDIT_INTERVIEW, interview.value.id)
    }

    private val onDeleteInterviewClick: () -> Unit = {
        navigator.openDeleteInterviewConfirmationScreen(REQUEST_KEY_DELETE_INTERVIEW_CONFIRM)
    }

    private val onEditInterviewResultClick: () -> Unit = {
        navigator.openSelectResultScreen(REQUEST_KEY_SELECT_RESULT, interview.value.result.name)
    }

}