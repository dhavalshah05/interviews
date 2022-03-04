package com.template.app.ui.addinterview

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.template.app.databinding.AddInterviewFragmentBinding
import com.template.app.domain.interviewers.models.Interviewer
import com.template.app.domain.managers.models.Manager
import com.template.app.ui.common.navigator.AppNavigator
import com.template.app.util.bundle.getParcelableValueOrError
import com.template.app.util.bundle.getSerializableValueOrError
import com.template.app.util.date.DateUtils
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class AddInterviewFragment : Fragment() {

    companion object {
        private const val REQUEST_KEY_SELECT_INTERVIEWER = "select_interviewer"
        private const val REQUEST_KEY_SELECT_MANAGER = "select_manager"
        private const val REQUEST_KEY_SELECT_DATE = "select_date"
    }

    @Inject
    lateinit var navigator: AppNavigator

    private var _binding: AddInterviewFragmentBinding? = null
    private val binding get() = _binding!!

    private var selectedInterviewer: Interviewer? = null
    private var selectedManager: Manager? = null
    private var selectedDate: Date? = null

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
            navigator.navigateToSelectInterviewerScreen(REQUEST_KEY_SELECT_INTERVIEWER, selectedInterviewer?.id)
        }

        binding.hrManager.setOnClickListener {
            navigator.navigateToSelectManagerScreen(REQUEST_KEY_SELECT_MANAGER, selectedManager?.id)
        }

        binding.interviewDate.setOnClickListener {
            openDatePicker()
        }
    }

    private fun openDatePicker() {
        navigator.navigateToSelectDateScreen(REQUEST_KEY_SELECT_DATE)
    }
}