package com.template.app.ui.interviews.interviewdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.template.app.domain.interviews.models.Interview
import com.template.app.domain.interviews.models.InterviewResult
import com.template.app.ui.common.navigator.AppNavigator
import com.template.app.util.bundle.getParcelableValueOrError
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InterviewDetailsFragment : Fragment() {

    companion object {
        private const val REQUEST_KEY_EDIT_INTERVIEW = "edit_interview"
        private const val REQUEST_KEY_SELECT_RESULT = "select_result"
        private const val REQUEST_KEY_DELETE_INTERVIEW_CONFIRM = "delete_interview_confirm"
        private const val BUNDLE_INTERVIEW = "INTERVIEW"

        fun createBundle(interview: Interview): Bundle {
            return Bundle().apply {
                putParcelable(BUNDLE_INTERVIEW, interview)
            }
        }
    }

    @Inject
    lateinit var navigator: AppNavigator

    private lateinit var interview: MutableState<Interview>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        interview = mutableStateOf(arguments.getParcelableValueOrError(BUNDLE_INTERVIEW))
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
        navigator.openEditInterviewScreen(REQUEST_KEY_EDIT_INTERVIEW, interview.value)
    }

    private val onDeleteInterviewClick: () -> Unit = {
        navigator.openDeleteInterviewConfirmationScreen(REQUEST_KEY_DELETE_INTERVIEW_CONFIRM)
    }

    private val onEditInterviewResultClick: () -> Unit = {
        navigator.openSelectResultScreen(REQUEST_KEY_SELECT_RESULT, interview.value.result)
    }

}