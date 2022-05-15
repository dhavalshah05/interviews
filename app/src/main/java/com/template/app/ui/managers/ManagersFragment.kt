package com.template.app.ui.managers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.template.app.domain.managers.models.Manager
import com.template.app.ui.PrototypeData
import com.template.app.ui.common.navigator.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ManagersFragment : Fragment() {

    companion object {
        private const val REQUEST_KEY_DELETE_MANAGER_CONFIRM = "delete_manager_confirm"
    }

    @Inject
    lateinit var navigator: AppNavigator

    private val managers: MutableState<List<Manager>> = mutableStateOf(PrototypeData.getManagers())

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
                    onBackClick = { navigator.goBack() },
                    onDeleteClick = { manager ->
                        navigator.openDeleteManagerConfirmationScreen(requestKeyDeleteManagerConfirm = REQUEST_KEY_DELETE_MANAGER_CONFIRM)
                    },
                    onAddClick = { navigator.openAddManagerScreen() }
                )
            }
        }
    }

}