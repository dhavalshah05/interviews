package com.template.app.ui.selectmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.template.app.databinding.SelectManagerItemBinding
import com.template.app.domain.managers.models.Manager
import com.template.app.ui.common.models.Selectable
import com.template.app.util.superadapter.BaseRecyclerViewAdapter
import com.template.app.util.superadapter.viewholder.BaseViewHolder

class SelectManagerAdapter : BaseRecyclerViewAdapter<Selectable<Manager>, SelectManagerAdapter.ViewHolder>() {

    interface Listener {
        fun onManagerClick(item: Selectable<Manager>)
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
        return ViewHolder(SelectManagerItemBinding.inflate(inflater, parent, false))
    }

    inner class ViewHolder(private val binding: SelectManagerItemBinding) :
        BaseViewHolder<Selectable<Manager>>(binding.root) {

        private lateinit var item: Selectable<Manager>

        init {
            binding.root.setOnClickListener {
                listener?.onManagerClick(item)
            }
        }

        override fun bind(item: Selectable<Manager>) {
            this.item = item
            binding.textViewId.text = String.format("#%s", item.item.id.toString())
            binding.textViewName.text = item.item.name
            binding.buttonTick.visibility = if (item.selected) View.VISIBLE else View.INVISIBLE
        }
    }
}