package com.template.app.ui.addinterviewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.template.app.R
import com.template.app.databinding.AddInterviewerBottomSheetBinding
import com.template.app.exception.ApplicationException
import com.template.app.ui.common.bottomsheet.TransparentBottomSheet
import com.template.app.util.alert.AlertNotification
import com.template.app.util.validator.Validator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddInterviewerBottomSheet : TransparentBottomSheet() {

    companion object {
        const val TAG = "add_interview"

        fun newInstance(): AddInterviewerBottomSheet {
            return AddInterviewerBottomSheet()
        }
    }

    @Inject
    lateinit var validator: Validator

    @Inject
    lateinit var alert: AlertNotification

    private var _binding: AddInterviewerBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return AddInterviewerBottomSheetBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = AddInterviewerBottomSheetBinding.bind(view)
        initViewListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViewListeners() {
        binding.buttonAdd.setOnClickListener {
            validateAndContinue()
        }
    }

    private fun validateAndContinue() {
        try {
            validator.submit(binding.interviewerName)
                .checkEmpty().errorMessage(getString(R.string.validation_interview_name_empty))
                .check()

            setResult()
        } catch (e: ApplicationException) {
            e.printStackTrace()
            val message = e.message ?: return
            alert.showErrorMessage(message = message, dialog = dialog)
        }
    }

    private fun setResult() {
        dismiss()
    }
}