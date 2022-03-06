package com.template.app.ui.managers.selectmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.template.app.databinding.SelectManagerBottomSheetBinding
import com.template.app.domain.managers.models.Manager
import com.template.app.ui.common.bottomsheet.TransparentBottomSheet
import com.template.app.ui.common.models.Selectable
import com.template.app.util.bundle.getLongValueOrError
import com.template.app.util.bundle.getStringValueOrError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectManagerBottomSheet : TransparentBottomSheet() {

    companion object {
        const val TAG = "select_manager"
        private const val BUNDLE_REQUEST_KEY_SELECT_MANAGER = "REQUEST_KEY_SELECT_MANAGER"
        private const val BUNDLE_MANAGER_ID = "MANAGER_ID"

        fun newInstance(
            requestKeySelectManager: String,
            managerId: Long?
        ): SelectManagerBottomSheet {
            val bundle = Bundle()
            bundle.putString(BUNDLE_REQUEST_KEY_SELECT_MANAGER, requestKeySelectManager)
            bundle.putLong(BUNDLE_MANAGER_ID, managerId ?: 0L)

            val dialog = SelectManagerBottomSheet()
            dialog.arguments = bundle
            return dialog
        }
    }

    private lateinit var adapter: SelectManagerAdapter

    private var _binding: SelectManagerBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val requestKeySelectManager: String by lazy {
        arguments.getStringValueOrError(BUNDLE_REQUEST_KEY_SELECT_MANAGER)
    }

    private val selectedManagerId: Long by lazy {
        arguments.getLongValueOrError(BUNDLE_MANAGER_ID)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = SelectManagerAdapter()
        adapter.setListener(adapterListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return SelectManagerBottomSheetBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SelectManagerBottomSheetBinding.bind(view)
        initRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val adapterListener = object : SelectManagerAdapter.Listener {
        override fun onManagerClick(item: Selectable<Manager>) {
            notifyParent(item.item)
        }
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewSelectManager.layoutManager = layoutManager
        binding.recyclerViewSelectManager.adapter = adapter
        adapter.replaceItems(getDummyManagers())
    }

    private fun getDummyManagers(): List<Selectable<Manager>> {
        val result = mutableListOf<Manager>()
        for (index in 1 until 20) {
            result.add(Manager(id = index.toLong(), name = "Manager $index"))
        }
        return result.map { Selectable(it, it.id == selectedManagerId) }
    }

    private fun notifyParent(manager: Manager) {
        val bundle = Bundle()
        bundle.putParcelable("manager", manager)
        parentFragmentManager.setFragmentResult(requestKeySelectManager, bundle)
        dismiss()
    }
}