package com.template.app.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.template.app.databinding.SettingsFragmentBinding
import com.template.app.ui.common.navigator.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    @Inject
    lateinit var navigator: AppNavigator

    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return SettingsFragmentBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SettingsFragmentBinding.bind(view)
        initToolbar()
        initViewListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            navigator.goBack()
        }
    }

    private fun initViewListeners() {
        binding.textViewManagers.setOnClickListener {
            navigator.navigateToManagersScreen()
        }
    }
}