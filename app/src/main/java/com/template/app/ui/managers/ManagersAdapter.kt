package com.template.app.ui.managers

import android.view.LayoutInflater
import android.view.ViewGroup
import com.template.app.databinding.ManagerItemBinding
import com.template.app.domain.managers.models.Manager
import com.template.app.util.superadapter.BaseRecyclerViewAdapter
import com.template.app.util.superadapter.viewholder.BaseViewHolder

class ManagersAdapter : BaseRecyclerViewAdapter<Manager, ManagersAdapter.ViewHolder>() {

    interface Listener {
        fun onDeleteManagerClick(item: Manager)
    }

    private var listener: Listener? = null

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    override fun getViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(ManagerItemBinding.inflate(inflater, parent, false))
    }

    inner class ViewHolder(private val binding: ManagerItemBinding) :
        BaseViewHolder<Manager>(binding.root) {

        private lateinit var manager: Manager

        init {
            binding.buttonDelete.setOnClickListener {
                listener?.onDeleteManagerClick(manager)
            }
        }

        override fun bind(item: Manager) {
            manager = item
            binding.textViewId.text = String.format("#%s", item.id.toString())
            binding.textViewName.text = item.name
        }
    }
}