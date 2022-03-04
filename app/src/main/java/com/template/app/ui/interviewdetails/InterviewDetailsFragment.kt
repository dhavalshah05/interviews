package com.template.app.ui.interviewdetails

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.template.app.R
import com.template.app.databinding.InterviewDetailsFragmentBinding
import com.template.app.domain.interviews.models.Interview
import com.template.app.domain.interviews.models.InterviewResult
import com.template.app.ui.common.navigator.AppNavigator
import com.template.app.ui.interviews.UiInterviewResult
import com.template.app.util.bundle.getParcelableValueOrError
import com.template.app.util.date.DateUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InterviewDetailsFragment : Fragment() {

    companion object {
        private const val REQUEST_KEY_EDIT_INTERVIEW = "edit_interview"
        private const val REQUEST_KEY_SELECT_RESULT = "select_result"
        private const val BUNDLE_INTERVIEW = "INTERVIEW"

        fun createBundle(interview: Interview): Bundle {
            return Bundle().apply {
                putParcelable(BUNDLE_INTERVIEW, interview)
            }
        }
    }

    @Inject
    lateinit var navigator: AppNavigator

    private var _binding: InterviewDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var interview: Interview

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        interview = arguments.getParcelableValueOrError(BUNDLE_INTERVIEW)
        parentFragmentManager.setFragmentResultListener(REQUEST_KEY_SELECT_RESULT, this) { _, result ->
            val interviewResult: InterviewResult = result.getParcelableValueOrError("interview_result")
            interview = interview.copy(result = interviewResult)
            bindInterviewResult(interviewResult)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return InterviewDetailsFragmentBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = InterviewDetailsFragmentBinding.bind(view)
        initToolbar()
        initViewListeners()
        bindInterview(interview)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            navigator.goBack()
        }
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menuEditInterview -> {
                    navigator.navigateToEditInterviewScreen(REQUEST_KEY_EDIT_INTERVIEW, interview)
                    true
                }
                else -> false
            }
        }
    }

    private fun initViewListeners() {
        binding.arrowInterviewComments.setOnClickListener {
            toggleViewVisibility(binding.interviewComments)
        }
        binding.arrowPracticalComments.setOnClickListener {
            toggleViewVisibility(binding.practicalComments)
        }
        binding.arrowPracticalLink.setOnClickListener {
            toggleViewVisibility(binding.practicalLink)
        }
        binding.buttonEditResult.setOnClickListener {
            navigator.navigateToSelectResultScreen(REQUEST_KEY_SELECT_RESULT, interview.result)
        }
    }

    private fun toggleViewVisibility(view: View) {
        val isVisible = view.visibility == View.VISIBLE
        view.visibility = if (isVisible) View.GONE else View.VISIBLE
    }

    private fun bindInterview(interview: Interview) {
        binding.candidateName.text = interview.candidateName
        binding.interviewDate.text = DateUtils.format(inputDate = interview.interviewDate, outFormat = DateUtils.DateFormat.PRIMARY_DATE)
        binding.experience.text = interview.experience
        binding.interviewer.text = interview.interviewer.name
        binding.manager.text = interview.manager.name

        bindInterviewResult(interview.result)

        // interview comments
        if (interview.interviewComments.isNotBlank()) {
            binding.interviewCommentsNA.visibility = View.GONE
            binding.arrowInterviewComments.visibility = View.VISIBLE
            binding.interviewComments.text = interview.interviewComments
        } else {
            binding.interviewCommentsNA.visibility = View.VISIBLE
            binding.arrowInterviewComments.visibility = View.GONE
        }

        // practical comments
        if (interview.practicalComments.isNotBlank()) {
            binding.practicalCommentsNA.visibility = View.GONE
            binding.arrowPracticalComments.visibility = View.VISIBLE
            binding.practicalComments.text = interview.practicalComments
        } else {
            binding.practicalCommentsNA.visibility = View.VISIBLE
            binding.arrowPracticalComments.visibility = View.GONE
        }

        // practical link
        if (interview.practicalLink.isNotBlank()) {
            binding.practicalLinkNA.visibility = View.GONE
            binding.arrowPracticalLink.visibility = View.VISIBLE
            binding.practicalLink.text = interview.practicalLink
        } else {
            binding.practicalLinkNA.visibility = View.VISIBLE
            binding.arrowPracticalLink.visibility = View.GONE
        }
    }

    private fun bindInterviewResult(result: InterviewResult) {
        val uiInterviewResult = UiInterviewResult.from(result)
        binding.textViewResult.text = getString(uiInterviewResult.textResId)
        binding.textViewResult.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                requireContext(),
                uiInterviewResult.bgColorResId
            )
        )
        binding.textViewResult.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                uiInterviewResult.textColorResId
            )
        )
    }
}