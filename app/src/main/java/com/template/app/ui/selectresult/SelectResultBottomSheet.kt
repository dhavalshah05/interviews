package com.template.app.ui.selectresult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.template.app.databinding.SelectResultBottomSheetBinding
import com.template.app.domain.interviews.models.InterviewResult
import com.template.app.ui.common.bottomsheet.TransparentBottomSheet
import com.template.app.ui.common.models.Selectable
import com.template.app.util.bundle.getParcelableValueOrError
import com.template.app.util.bundle.getStringValueOrError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectResultBottomSheet : TransparentBottomSheet() {

    companion object {
        const val TAG = "select_result"
        private const val BUNDLE_REQUEST_KEY_SELECT_RESULT = "REQUEST_KEY_SELECT_RESULT"
        private const val BUNDLE_INTERVIEW_RESULT = "INTERVIEW_RESULT"

        fun newInstance(
            requestKeySelectResult: String,
            interviewResult: InterviewResult
        ): SelectResultBottomSheet {
            val bundle = Bundle()
            bundle.putString(BUNDLE_REQUEST_KEY_SELECT_RESULT, requestKeySelectResult)
            bundle.putParcelable(BUNDLE_INTERVIEW_RESULT, interviewResult)

            val dialog = SelectResultBottomSheet()
            dialog.arguments = bundle
            return dialog
        }
    }

    private lateinit var adapter: SelectResultAdapter

    private var _binding: SelectResultBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val requestKeySelectResult: String by lazy {
        arguments.getStringValueOrError(BUNDLE_REQUEST_KEY_SELECT_RESULT)
    }

    private val interviewResult: InterviewResult by lazy {
        arguments.getParcelableValueOrError(BUNDLE_INTERVIEW_RESULT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = SelectResultAdapter()
        adapter.setListener(adapterListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return SelectResultBottomSheetBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SelectResultBottomSheetBinding.bind(view)
        initRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val adapterListener = object : SelectResultAdapter.Listener {
        override fun onResultClick(item: Selectable<InterviewResult>) {
            notifyParent(item.item)
        }
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewSelectResult.layoutManager = layoutManager
        binding.recyclerViewSelectResult.adapter = adapter
        adapter.replaceItems(
            InterviewResult.values().toList().map { Selectable(it, it == interviewResult) })
    }

    private fun notifyParent(result: InterviewResult) {
        val bundle = Bundle()
        bundle.putParcelable("interview_result", result)
        parentFragmentManager.setFragmentResult(requestKeySelectResult, bundle)
        dismiss()
    }
}