package com.template.app.ui.managers.selectmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.template.app.domain.managers.models.Manager
import com.template.app.ui.PrototypeData
import com.template.app.ui.common.bottomsheet.TransparentBottomSheet
import com.template.app.ui.common.models.Selectable
import com.template.app.util.bundle.getLongValueOrError
import com.template.app.util.bundle.getStringValueOrError
import com.template.app.util.list.updateItem
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

    private val requestKeySelectManager: String by lazy {
        arguments.getStringValueOrError(BUNDLE_REQUEST_KEY_SELECT_MANAGER)
    }

    private val items = mutableStateOf(PrototypeData.getManagers().map { Selectable(item = it, selected = false) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val selectedManagerId = arguments.getLongValueOrError(BUNDLE_MANAGER_ID)
        val selectableItem = items.value.find { it.item.id == selectedManagerId }
        if (selectableItem != null) {
            items.value = items.value.updateItem(selectableItem.copy(selected = true)) {
                it.item.id == selectableItem.item.id
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.Default)
            setContent {
                SelectManagerScreen(
                    items = items.value,
                    onItemSelect = { notifyParent(it.item) }
                )
            }
        }
    }

    private fun notifyParent(manager: Manager) {
        val bundle = Bundle()
        bundle.putParcelable("manager", manager)
        parentFragmentManager.setFragmentResult(requestKeySelectManager, bundle)
        dismiss()
    }
}