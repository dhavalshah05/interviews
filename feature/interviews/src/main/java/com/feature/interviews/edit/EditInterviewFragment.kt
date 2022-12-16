package com.feature.interviews.edit

import android.os.Build
import android.os.Bundle
import android.text.InputFilter
import android.view.*
import androidx.fragment.app.Fragment
import com.data.interviewers.Interviewer
import com.data.interviews.Interview
import com.data.interviews.InterviewResult
import com.data.interviews.PreviewData
import com.data.managers.Manager
import com.feature.interviews.databinding.EditInterviewFragmentBinding
import com.feature.interviews.util.DecimalDigitsInputFilter
import com.feature.interviews.util.Validator
import com.service.navigation.Navigator
import com.service.presentation.R
import com.service.presentation.utils.bundle.getLongValueOrError
import com.service.presentation.utils.bundle.getParcelableValueOrError
import com.service.presentation.utils.bundle.getSerializableValueOrError
import com.service.presentation.utils.bundle.getStringValueOrError
import com.service.presentation.utils.superdatetime.DateTimePattern
import com.service.presentation.utils.superdatetime.SuperDateTime
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class EditInterviewFragment : Fragment() {

    companion object {
        private const val REQUEST_KEY_SELECT_INTERVIEWER = "select_interviewer"
        private const val REQUEST_KEY_SELECT_MANAGER = "select_manager"
        private const val REQUEST_KEY_SELECT_DATE = "select_date"

        private const val BUNDLE_REQUEST_KEY_EDIT_INTERVIEW = "REQUEST_KEY_EDIT_INTERVIEW"
        private const val BUNDLE_INTERVIEW_ID = "INTERVIEW_ID"

        fun createBundle(requestKeyEditInterview: String, interviewId: Long): Bundle {
            return Bundle().apply {
                putString(BUNDLE_REQUEST_KEY_EDIT_INTERVIEW, requestKeyEditInterview)
                putLong(BUNDLE_INTERVIEW_ID, interviewId)
            }
        }
    }

    @Inject
    lateinit var navigator: Navigator
    
    private lateinit var validator: Validator

    private var _binding: EditInterviewFragmentBinding? = null
    private val binding get() = _binding!!

    private val scope = CoroutineScope(Dispatchers.Main.immediate)

    private var selectedInterviewer: Interviewer? = null
    private var selectedManager: Manager? = null
    private var selectedDate: Date? = null

    private val requestKeyAddInterview: String by lazy {
        arguments.getStringValueOrError(BUNDLE_REQUEST_KEY_EDIT_INTERVIEW)
    }

    private val interview: Interview by lazy {
        val id = arguments.getLongValueOrError(BUNDLE_INTERVIEW_ID)
        PreviewData.getInterviews().first()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        validator = Validator()
        selectedInterviewer = com.data.interviewers.PreviewData.getInterviewers().first()
        selectedManager = com.data.managers.PreviewData.getManagers().first()
        selectedDate = Date(interview.interviewDate)
        observeFragmentResults()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return EditInterviewFragmentBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = EditInterviewFragmentBinding.bind(view)
        registerWindowInsetListener()
        binding.experience.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(2, 2))
        initToolbar()
        initViewListeners()
        bindInterview()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()

        scope.launch {
            delay(500)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                requireActivity().window.setDecorFitsSystemWindows(false)
            } else {
                requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            }
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

    private fun bindInterview() {
        binding.candidateName.setText(interview.candidateName)
        binding.experience.setText(interview.experience)

        val dateTime = SuperDateTime.toDateTime(timestamp = interview.interviewDate)
        val dateString = SuperDateTime.toText(dateTime = dateTime, outputPattern = DateTimePattern.PRIMARY)
        binding.interviewDate.setText(dateString)

        binding.hrManager.setText(interview.managerName)
        binding.interviewer.setText(interview.interviewerName)
        binding.interviewComments.setText(interview.interviewComments)
        binding.practicalComments.setText(interview.practicalComments)
        binding.practicalLink.setText(interview.practicalLink)
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
            val dateTime = SuperDateTime.toDateTime(date = date)
            val formattedDate = SuperDateTime.toText(dateTime, outputPattern = DateTimePattern.PRIMARY)
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

            val interviewDate = selectedDate ?: throw Exception(getString(R.string.validation_select_interview_date))
            val manager = selectedManager ?: throw Exception(getString(R.string.validation_select_manager))
            val interviewer = selectedInterviewer ?: throw Exception(getString(R.string.validation_select_interviewer))

            val updatedInterview = interview.copy(
                candidateName = binding.candidateName.text.toString(),
                experience = binding.experience.text.toString(),
                interviewDate = interviewDate.time,
                result = InterviewResult.PENDING,
                interviewerId = interviewer.id,
                interviewerName = interviewer.name,
                managerId = manager.id,
                managerName = manager.name,
                interviewComments = binding.interviewComments.text.toString(),
                practicalComments = binding.practicalComments.text.toString(),
                practicalLink = binding.practicalLink.text.toString()
            )

            setResult(updatedInterview)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setResult(interview: Interview) {
        val bundle = Bundle()
        bundle.putParcelable("interview", interview)
        parentFragmentManager.setFragmentResult(requestKeyAddInterview, bundle)
        //alert.showSuccessMessage(activity = requireActivity(), message = getString(R.string.alert_message_interview_updated))
        navigator.goBack()
    }
}