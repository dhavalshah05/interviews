package com.template.app.ui.interviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.template.app.R
import com.template.app.databinding.InterviewsFragmentBinding
import com.template.app.domain.interviewers.models.Interviewer
import com.template.app.domain.interviews.models.Interview
import com.template.app.domain.interviews.models.InterviewResult
import com.template.app.domain.managers.models.Manager
import com.template.app.ui.common.navigator.AppNavigator
import com.template.app.util.display.DisplayMetrics
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InterviewsFragment : Fragment() {

    @Inject
    lateinit var navigator: AppNavigator

    private var _binding: InterviewsFragmentBinding? = null
    private val binding get() = _binding!!

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

        }
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext())
        val adapter = InterviewsAdapter()
        adapter.setListener(interviewsAdapterListener)
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
        navigator.navigateToSettingsScreen()
    }

    private fun getDummyInterviews(): List<Interview> {
        val result = mutableListOf<Interview>()
        for (index in 1 until 10) {
            result.add(
                Interview(
                    id = index.toLong(),
                    candidateName = "John Doe",
                    experience = "2.3",
                    interviewDate = System.currentTimeMillis(),
                    result = InterviewResult.PENDING,
                    interviewer = Interviewer(id = 1, name = "Dhaval Shah"),
                    manager = Manager(id = 1, name = "Khyati Shah"),
                    interviewComments = "",
                    practicalComments = "",
                    practicalLink = ""
                )
            )
        }
        return result
    }
}