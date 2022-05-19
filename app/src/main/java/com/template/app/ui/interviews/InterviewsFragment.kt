package com.template.app.ui.interviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.template.app.domain.interviews.models.Interview
import com.template.app.ui.PrototypeData
import com.template.app.ui.common.navigator.AppNavigator
import com.template.app.util.bundle.getParcelableValueOrError
import com.template.app.util.list.addItemAtStart
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InterviewsFragment : Fragment() {

    companion object {

        private const val REQUEST_KEY_ADD_INTERVIEW = "add_interview"
    }

    @Inject
    lateinit var navigator: AppNavigator

    private val interviews = mutableStateOf(PrototypeData.getInterviews())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        parentFragmentManager.setFragmentResultListener(
            REQUEST_KEY_ADD_INTERVIEW,
            this
        ) { _, result ->
            val interview: Interview = result.getParcelableValueOrError("interview")
            interviews.value = interviews.value.addItemAtStart(interview)
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
                InterviewsScreen(
                    interviews = interviews.value,
                    onSettingsClick = onSettingsClick,
                    onSearchTextChange = onSearchTextChange,
                    onInterviewCardClick = onInterviewCardClick
                )
            }
        }
    }

    private val onSettingsClick = {
        navigator.openSettingsScreen()
    }

    private val onSearchTextChange = { _: String ->

    }

    private val onInterviewCardClick = { interview: Interview ->
        navigator.openInterviewDetailsScreen(interview)
    }

    // navigator.openAddInterviewScreen(REQUEST_KEY_ADD_INTERVIEW)

}