package com.template.app.ui.addinterview

import android.os.Build
import android.os.Bundle
import android.text.InputFilter
import android.view.*
import androidx.fragment.app.Fragment
import com.template.app.R
import com.template.app.databinding.AddInterviewFragmentBinding
import com.template.app.domain.interviewers.models.Interviewer
import com.template.app.domain.interviews.models.Interview
import com.template.app.domain.interviews.models.InterviewResult
import com.template.app.domain.managers.models.Manager
import com.template.app.exception.ApplicationException
import com.template.app.ui.common.navigator.AppNavigator
import com.template.app.util.alert.AlertNotification
import com.template.app.util.bundle.getParcelableValueOrError
import com.template.app.util.bundle.getSerializableValueOrError
import com.template.app.util.bundle.getStringValueOrError
import com.template.app.util.date.DateUtils
import com.template.app.util.edittext.DecimalDigitsInputFilter
import com.template.app.util.validator.Validator
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class AddInterviewFragment : Fragment() {

    companion object {
        private const val REQUEST_KEY_SELECT_INTERVIEWER = "select_interviewer"
        private const val REQUEST_KEY_SELECT_MANAGER = "select_manager"
        private const val REQUEST_KEY_SELECT_DATE = "select_date"

        private const val BUNDLE_REQUEST_KEY_ADD_INTERVIEW = "REQUEST_KEY_ADD_INTERVIEW"

        fun createBundle(requestKeyAddInterview: String): Bundle {
            return Bundle().apply {
                putString(BUNDLE_REQUEST_KEY_ADD_INTERVIEW, requestKeyAddInterview)
            }
        }
    }

    @Inject
    lateinit var navigator: AppNavigator

    @Inject
    lateinit var validator: Validator

    @Inject
    lateinit var alert: AlertNotification

    private var _binding: AddInterviewFragmentBinding? = null
    private val binding get() = _binding!!

    private var selectedInterviewer: Interviewer? = null
    private var selectedManager: Manager? = null
    private var selectedDate: Date? = null

    private val requestKeyAddInterview: String by lazy {
        arguments.getStringValueOrError(BUNDLE_REQUEST_KEY_ADD_INTERVIEW)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeFragmentResults()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return AddInterviewFragmentBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = AddInterviewFragmentBinding.bind(view)
        registerWindowInsetListener()
        binding.experience.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(2, 2))
        initToolbar()
        initViewListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().window.setDecorFitsSystemWindows(false)
        } else {
            requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().window.setDecorFitsSystemWindows(true)
        } else {
            requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        }
    }

    private fun observeFragmentResults() {
        parentFragmentManager.setFragmentResultListener(
            REQUEST_KEY_SELECT_INTERVIEWER,
            this
        ) { _, result ->
            val interviewer: Interviewer = result.getParcelableValueOrError("interviewer")
            selectedInterviewer = interviewer
            binding.interviewer.setText(interviewer.name)
        }
        parentFragmentManager.setFragmentResultListener(
            REQUEST_KEY_SELECT_MANAGER,
            this
        ) { _, result ->
            val manager: Manager = result.getParcelableValueOrError("manager")
            selectedManager = manager
            binding.hrManager.setText(manager.name)
        }
        parentFragmentManager.setFragmentResultListener(
            REQUEST_KEY_SELECT_DATE,
            this
        ) { _, result ->
            val date: Date = result.getSerializableValueOrError("date")
            selectedDate = date
            val formattedDate =
                DateUtils.format(date, outFormat = DateUtils.DateFormat.PRIMARY_DATE)
            binding.interviewDate.setText(formattedDate)
        }
    }

    private fun registerWindowInsetListener() {
        binding.root.setOnApplyWindowInsetsListener { _, windowInsets ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                // Bottom padding
                val imeHeight = windowInsets.getInsets(WindowInsets.Type.ime()).bottom
                val navigationBarHeight = windowInsets.getInsets(WindowInsets.Type.navigationBars()).bottom
                val paddingBottom = if (imeHeight > 0) imeHeight else navigationBarHeight
                binding.root.setPadding(0, 0, 0, paddingBottom)

                // Top margin
                val marginTop = windowInsets.getInsets(WindowInsets.Type.statusBars()).top
                val lp = binding.toolbar.layoutParams as ViewGroup.MarginLayoutParams
                lp.setMargins(0, marginTop, 0, 0)
                binding.toolbar.layoutParams = lp
            }

            windowInsets
        }
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            navigator.goBack()
        }
    }

    private fun initViewListeners() {
        binding.interviewer.setOnClickListener {
            navigator.openSelectInterviewerScreen(REQUEST_KEY_SELECT_INTERVIEWER, selectedInterviewer?.id)
        }

        binding.hrManager.setOnClickListener {
            navigator.openSelectManagerScreen(REQUEST_KEY_SELECT_MANAGER, selectedManager?.id)
        }

        binding.interviewDate.setOnClickListener {
            navigator.openSelectDateScreen(REQUEST_KEY_SELECT_DATE)
        }

        binding.buttonAdd.setOnClickListener {
            validateAndContinue()
        }
    }

    private fun validateAndContinue() {
        try {
            validator.submit(binding.candidateName)
                .checkEmpty().errorMessage(getString(R.string.validation_candidate_name_empty))
                .check()

            validator.submit(binding.experience)
                .checkEmpty().errorMessage(getString(R.string.validation_experience_empty))
                .check()

            val interviewDate = selectedDate ?: throw ApplicationException(getString(R.string.validation_select_interview_date))
            val manager = selectedManager ?: throw ApplicationException(getString(R.string.validation_select_manager))
            val interviewer = selectedInterviewer ?: throw ApplicationException(getString(R.string.validation_select_interviewer))

            val interview = Interview(
                id = 0L,
                candidateName = binding.candidateName.text.toString(),
                experience = binding.experience.text.toString(),
                interviewDate = interviewDate.time,
                result = InterviewResult.PENDING,
                interviewer = interviewer,
                manager = manager,
                interviewComments = binding.interviewComments.text.toString(),
                practicalComments = binding.practicalComments.text.toString(),
                practicalLink = binding.practicalLink.text.toString()
            )

            setResult(interview)
        } catch (e: ApplicationException) {
            e.printStackTrace()
            val message = e.message ?: return
            alert.showErrorMessage(activity = requireActivity(), message = message)
        }
    }

    private fun setResult(interview: Interview) {
        val bundle = Bundle()
        bundle.putParcelable("interview", interview)
        parentFragmentManager.setFragmentResult(requestKeyAddInterview, bundle)
        alert.showSuccessMessage(activity = requireActivity(), message = getString(R.string.alert_message_interview_added))
        navigator.goBack()
    }
}