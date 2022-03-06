package com.template.app.ui.interviewers.selectinterviewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.template.app.databinding.SelectInterviewerBottomSheetBinding
import com.template.app.domain.interviewers.models.Interviewer
import com.template.app.ui.common.bottomsheet.TransparentBottomSheet
import com.template.app.ui.common.models.Selectable
import com.template.app.util.bundle.getLongValueOrError
import com.template.app.util.bundle.getStringValueOrError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectInterviewerBottomSheet : TransparentBottomSheet() {

    companion object {
        const val TAG = "select_interviewer"
        private const val BUNDLE_REQUEST_KEY_SELECT_INTERVIEWER = "REQUEST_KEY_SELECT_INTERVIEWER"
        private const val BUNDLE_INTERVIEWER_ID = "INTERVIEWER_ID"

        fun newInstance(requestKeySelectInterviewer: String, interviewerId: Long?): SelectInterviewerBottomSheet {
            val bundle = Bundle()
            bundle.putString(BUNDLE_REQUEST_KEY_SELECT_INTERVIEWER, requestKeySelectInterviewer)
            bundle.putLong(BUNDLE_INTERVIEWER_ID, interviewerId ?: 0L)

            val dialog = SelectInterviewerBottomSheet()
            dialog.arguments = bundle
            return dialog
        }
    }

    private lateinit var adapter: SelectInterviewerAdapter

    private var _binding: SelectInterviewerBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val requestKeySelectInterviewer: String by lazy {
        arguments.getStringValueOrError(BUNDLE_REQUEST_KEY_SELECT_INTERVIEWER)
    }

    private val selectedInterviewId: Long by lazy {
        arguments.getLongValueOrError(BUNDLE_INTERVIEWER_ID)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = SelectInterviewerAdapter()
        adapter.setListener(adapterListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return SelectInterviewerBottomSheetBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SelectInterviewerBottomSheetBinding.bind(view)
        initRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val adapterListener = object : SelectInterviewerAdapter.Listener {
        override fun onInterviewerClick(item: Selectable<Interviewer>) {
            notifyParent(item.item)
        }
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewSelectInterviewers.layoutManager = layoutManager
        binding.recyclerViewSelectInterviewers.adapter = adapter
        adapter.replaceItems(getDummyInterviewers())
    }

    private fun getDummyInterviewers(): List<Selectable<Interviewer>> {
        val result = mutableListOf<Interviewer>()
        for (index in 1 until 20) {
            result.add(Interviewer(id = index.toLong(), name = "Interviewer $index"))
        }
        return result.map { Selectable(it, it.id == selectedInterviewId) }
    }

    private fun notifyParent(interviewer: Interviewer) {
        val bundle = Bundle()
        bundle.putParcelable("interviewer", interviewer)
        parentFragmentManager.setFragmentResult(requestKeySelectInterviewer, bundle)
        dismiss()
    }
}