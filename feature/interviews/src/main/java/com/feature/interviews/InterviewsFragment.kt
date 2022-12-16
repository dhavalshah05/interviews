package com.feature.interviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.data.interviews.Interview
import com.data.interviews.PreviewData
import com.service.navigation.Navigator
import com.service.presentation.utils.bundle.getParcelableValueOrError
import com.service.presentation.utils.list.addItemAtStart
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InterviewsFragment : Fragment() {

    companion object {

        private const val REQUEST_KEY_ADD_INTERVIEW = "add_interview"
    }

    @Inject
    lateinit var navigator: Navigator

    private val interviews = mutableStateOf(PreviewData.getInterviews())

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
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnDetachedFromWindow)
            setContent {
                InterviewsScreen(
                    interviews = interviews.value,
                    onSettingsClick = onSettingsClick,
                    onSearchTextChange = onSearchTextChange,
                    onInterviewCardClick = onInterviewCardClick,
                    onAddNewClick = onAddNewClick
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
        navigator.openInterviewDetailsScreen(interview.id)
    }

    private val onAddNewClick = {
        navigator.openAddInterviewScreen(REQUEST_KEY_ADD_INTERVIEW)
    }

}