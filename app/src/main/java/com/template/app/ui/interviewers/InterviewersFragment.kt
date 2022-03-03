package com.template.app.ui.interviewers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.template.app.databinding.InterviewersFragmentBinding
import com.template.app.domain.interviewers.models.Interviewer
import com.template.app.ui.common.navigator.AppNavigator
import com.template.app.util.display.DisplayMetrics
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InterviewersFragment : Fragment() {

    @Inject
    lateinit var navigator: AppNavigator

    private var _binding: InterviewersFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return InterviewersFragmentBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = InterviewersFragmentBinding.bind(view)
        setRecyclerViewPadding()
        initToolbar()
        initRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            navigator.goBack()
        }
    }

    private val interviewersAdapterListener = object : InterviewersAdapter.Listener {
        override fun onDeleteInterviewerClick(item: Interviewer) {

        }
    }

    private fun setRecyclerViewPadding() {
        binding.buttonAddNew.post {
            val buttonHeight = binding.buttonAddNew.height
            val marginAround = DisplayMetrics.convertDpToPixel(40f).toInt()
            val paddingBottom = buttonHeight + marginAround
            binding.recyclerViewInterviewers.setPaddingRelative(
                0,
                0,
                0,
                paddingBottom
            )
            binding.recyclerViewInterviewers.clipToPadding = false
        }
    }

    private fun initRecyclerView() {
        val layoutInterviewer = LinearLayoutManager(requireContext())
        val adapter = InterviewersAdapter()
        adapter.setListener(interviewersAdapterListener)
        binding.recyclerViewInterviewers.layoutManager = layoutInterviewer
        binding.recyclerViewInterviewers.adapter = adapter
        adapter.replaceItems(getDummyInterviewers())
    }

    private fun getDummyInterviewers(): List<Interviewer> {
        val result = mutableListOf<Interviewer>()
        for (index in 1 until 20) {
            result.add(Interviewer(id = index.toLong(), name = "Interviewer $index"))
        }
        return result
    }
}