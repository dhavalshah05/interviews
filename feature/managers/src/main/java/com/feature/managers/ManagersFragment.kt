package com.feature.managers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.data.managers.Manager
import com.data.managers.PreviewData
import com.service.navigation.Navigator
import com.service.presentation.utils.alert.AlertMessage
import com.service.presentation.utils.list.deleteItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ManagersFragment : Fragment() {

    companion object {
        private const val REQUEST_KEY_DELETE_MANAGER_CONFIRM = "delete_manager_confirm"
    }

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var alertMessage: AlertMessage

    private val managers: MutableState<List<Manager>> = mutableStateOf(PreviewData.getManagers())
    private var selectedManager: Manager? = null

    private val onBackClick: () -> Unit = { navigator.goBack() }

    private val onDeleteClick: (Manager) -> Unit = {
        this.selectedManager = it
        navigator.openDeleteManagerConfirmationScreen(requestKeyDeleteManagerConfirm = REQUEST_KEY_DELETE_MANAGER_CONFIRM)
    }

    private val onAddClick: () -> Unit = {
        navigator.openAddManagerScreen()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentFragmentManager.setFragmentResultListener(
            REQUEST_KEY_DELETE_MANAGER_CONFIRM,
            this
        ) { _, _ ->
            onDeleteManager()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ManagersScreen(
                    managers = managers.value,
                    onBackClick = onBackClick,
                    onDeleteClick = onDeleteClick,
                    onAddClick = onAddClick
                )
            }
        }
    }

    private fun onDeleteManager() {
        val manager = selectedManager ?: return
        var managers = managers.value
        managers = managers.deleteItem { it.id == manager.id }
        this.managers.value = managers
        alertMessage.success(getString(com.service.presentation.R.string.alert_message_manager_deleted))
    }

}