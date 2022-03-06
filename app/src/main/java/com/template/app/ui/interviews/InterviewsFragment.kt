package com.template.app.ui.interviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.template.app.R
import com.template.app.databinding.InterviewsFragmentBinding
import com.template.app.domain.interviewers.models.Interviewer
import com.template.app.domain.interviews.models.Interview
import com.template.app.domain.interviews.models.InterviewResult
import com.template.app.domain.managers.models.Manager
import com.template.app.ui.common.navigator.AppNavigator
import com.template.app.util.bundle.getParcelableValueOrError
import com.template.app.util.display.DisplayMetrics
import com.template.app.util.keyboard.KeyboardVisibilityHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InterviewsFragment : Fragment() {

    companion object {
        private const val REQUEST_KEY_ADD_INTERVIEW = "add_interview"
    }

    @Inject
    lateinit var navigator: AppNavigator

    @Inject
    lateinit var keyboardVisibilityHandler: KeyboardVisibilityHandler

    private var _binding: InterviewsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: InterviewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = InterviewsAdapter()
        adapter.setListener(interviewsAdapterListener)

        parentFragmentManager.setFragmentResultListener(
            REQUEST_KEY_ADD_INTERVIEW,
            this
        ) { _, result ->
            val interview: Interview = result.getParcelableValueOrError("interview")
            adapter.appendItemsAt(0, listOf(interview))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return InterviewsFragmentBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = InterviewsFragmentBinding.bind(view)
        setRecyclerViewPadding()
        initRecyclerView()
        initToolbar()
        initViewListeners()
    }

    private fun initViewListeners() {
        binding.buttonAddNew.setOnClickListener {
            navigator.openAddInterviewScreen(REQUEST_KEY_ADD_INTERVIEW)
        }
        binding.searchInterview.doOnTextChanged { text, _, _, _ ->
            onSearchTextChanged(text.toString())
        }
        binding.crossButton.setOnClickListener {
            clearSearchText()
        }
    }

    private fun clearSearchText() {
        binding.searchInterview.text?.clear()
        keyboardVisibilityHandler.hideKeyboard()
        binding.searchInterview.clearFocus()
    }

    private fun onSearchTextChanged(text: String) {
        binding.crossButton.visibility = if (text.isEmpty()) View.GONE else View.VISIBLE
    }

    private fun setRecyclerViewPadding() {
        binding.buttonAddNew.post {
            val buttonHeight = binding.buttonAddNew.height
            val buttonMarginBottom = DisplayMetrics.convertDpToPixel(20f).toInt()
            val paddingBottom = buttonHeight + buttonMarginBottom
            val paddingHorizontal = DisplayMetrics.convertDpToPixel(20f).toInt()
            binding.recyclerViewInterviews.setPaddingRelative(
                paddingHorizontal,
                0,
                paddingHorizontal,
                paddingBottom
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val interviewsAdapterListener = object : InterviewsAdapter.Listener {
        override fun onInterviewClick(item: Interview) {
            navigator.openInterviewDetailsScreen(item)
        }
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewInterviews.layoutManager = layoutManager
        binding.recyclerViewInterviews.adapter = adapter
        adapter.replaceItems(getDummyInterviews())
    }

    private fun initToolbar() {
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menuSetting -> {
                    openSettingsScreen()
                    true
                }
                R.id.menuFilters -> {
                    true
                }
                else -> false
            }
        }
    }

    private fun openSettingsScreen() {
        navigator.openSettingsScreen()
    }

    private fun getDummyInterviews(): List<Interview> {
        val result = mutableListOf<Interview>()
        for (index in 1 until 10) {
            result.add(
                Interview(
                    id = index.toLong(),
                    candidateName = "John Doe",
                    experience = index.toString(),
                    interviewDate = System.currentTimeMillis(),
                    result = InterviewResult.PENDING,
                    interviewer = Interviewer(id = 1, name = "Dhaval Shah"),
                    manager = Manager(id = 1, name = "Khyati Shah"),
                    interviewComments = if (index % 2 == 0) "" else "Demo interview comments",
                    practicalComments = "",
                    practicalLink = ""
                )
            )
        }
        return result
    }
}