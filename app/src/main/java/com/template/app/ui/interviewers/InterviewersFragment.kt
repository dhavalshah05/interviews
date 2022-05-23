package com.template.app.ui.interviewers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.template.app.domain.interviewers.models.Interviewer
import com.template.app.ui.PrototypeData
import com.template.app.ui.common.navigator.AppNavigator
import com.template.app.util.list.deleteItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InterviewersFragment : Fragment() {

    companion object {
        private const val REQUEST_KEY_DELETE_INTERVIEWER_CONFIRM = "delete_interviewer_confirm"
    }

    @Inject
    lateinit var navigator: AppNavigator

    private val interviewers: MutableState<List<Interviewer>> = mutableStateOf(PrototypeData.getInterviewers())
    private var selectedInterviewer: Interviewer? = null

    private val onBackClick: () -> Unit = { navigator.goBack() }

    private val onDeleteClick: (Interviewer) -> Unit = {
        this.selectedInterviewer = it
        navigator.openDeleteInterviewerConfirmationScreen(requestKeyDeleteInterviewerConfirm = REQUEST_KEY_DELETE_INTERVIEWER_CONFIRM)
    }

    private val onAddClick: () -> Unit = {
        navigator.openAddInterviewerScreen()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentFragmentManager.setFragmentResultListener(
            REQUEST_KEY_DELETE_INTERVIEWER_CONFIRM,
            this
        ) { _, _ ->
            onDeleteInterviewer()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                InterviewersScreen(
                    interviewers = interviewers.value,
                    onBackClick = onBackClick,
                    onDeleteClick = onDeleteClick,
                    onAddClick = onAddClick
                )
            }
        }
    }

    private fun onDeleteInterviewer() {
        val interviewer = selectedInterviewer ?: return
        interviewers.value = interviewers.value
            .deleteItem { it.id == interviewer.id }
    }

}