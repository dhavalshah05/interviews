package com.feature.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.service.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import com.service.presentation.R
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    @Inject
    lateinit var navigator: Navigator

    private val settingOptions = mutableStateOf(
        listOf(
            SettingOption(id = 1, title = R.string.label_hr_managers),
            SettingOption(id = 2, title = R.string.label_interviewers),
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.Default)
            setContent {
                SettingsScreen(
                    settingOptions = settingOptions.value,
                    onBackClick = onBackClick,
                    onSettingOptionClick = onSettingOptionClick
                )
            }
        }
    }

    private val onBackClick: () -> Unit = {
        navigator.goBack()
    }

    private val onSettingOptionClick: (SettingOption) -> Unit = { settingOption ->
        if (settingOption.title == R.string.label_hr_managers) {
            navigator.openManagersScreen()
        }

        if (settingOption.title == R.string.label_interviewers) {
            navigator.openInterviewersScreen()
        }
    }

}