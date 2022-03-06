package com.template.app.ui.addmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.template.app.R
import com.template.app.databinding.AddManagerBottomSheetBinding
import com.template.app.exception.ApplicationException
import com.template.app.ui.common.bottomsheet.TransparentBottomSheet
import com.template.app.util.alert.AlertNotification
import com.template.app.util.validator.Validator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddManagerBottomSheet : TransparentBottomSheet() {

    companion object {
        const val TAG = "add_manager"

        fun newInstance(): AddManagerBottomSheet {
            return AddManagerBottomSheet()
        }
    }

    @Inject
    lateinit var validator: Validator

    @Inject
    lateinit var alert: AlertNotification

    private var _binding: AddManagerBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return AddManagerBottomSheetBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = AddManagerBottomSheetBinding.bind(view)
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
            validator.submit(binding.managerName)
                .checkEmpty().errorMessage(getString(R.string.validation_manager_name_empty))
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