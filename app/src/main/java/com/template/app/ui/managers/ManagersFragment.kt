package com.template.app.ui.managers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.template.app.databinding.ManagersFragmentBinding
import com.template.app.domain.managers.models.Manager
import com.template.app.ui.common.navigator.AppNavigator
import com.template.app.util.display.DisplayMetrics
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ManagersFragment : Fragment() {

    @Inject
    lateinit var navigator: AppNavigator

    private var _binding: ManagersFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ManagersFragmentBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ManagersFragmentBinding.bind(view)
        setRecyclerViewPadding()
        initToolbar()
        initRecyclerView()
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

    private val managersAdapterListener = object : ManagersAdapter.Listener {
        override fun onDeleteManagerClick(item: Manager) {

        }
    }

    private fun setRecyclerViewPadding() {
        binding.buttonAddNew.post {
            val buttonHeight = binding.buttonAddNew.height
            val marginAround = DisplayMetrics.convertDpToPixel(40f).toInt()
            val paddingBottom = buttonHeight + marginAround
            binding.recyclerViewManagers.setPaddingRelative(
                0,
                0,
                0,
                paddingBottom
            )
            binding.recyclerViewManagers.clipToPadding = false
        }
    }
    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext())
        val adapter = ManagersAdapter()
        adapter.setListener(managersAdapterListener)
        binding.recyclerViewManagers.layoutManager = layoutManager
        binding.recyclerViewManagers.adapter = adapter
        adapter.replaceItems(getDummyManagers())
    }

    private fun initViewListeners() {
        binding.buttonAddNew.setOnClickListener {
            navigator.openAddManagerScreen()
        }
    }

    private fun getDummyManagers(): List<Manager> {
        val result = mutableListOf<Manager>()
        for (index in 1 until 20) {
            result.add(Manager(id = index.toLong(), name = "Manager $index"))
        }
        return result
    }
}