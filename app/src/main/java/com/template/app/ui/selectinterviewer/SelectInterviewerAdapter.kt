package com.template.app.ui.selectinterviewer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.template.app.databinding.SelectInterviewerItemBinding
import com.template.app.domain.interviewers.models.Interviewer
import com.template.app.util.superadapter.BaseRecyclerViewAdapter
import com.template.app.util.superadapter.viewholder.BaseViewHolder

class SelectInterviewerAdapter : BaseRecyclerViewAdapter<Selectable<Interviewer>, SelectInterviewerAdapter.ViewHolder>() {

    interface Listener {
        fun onInterviewerClick(item: Selectable<Interviewer>)
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
        return ViewHolder(SelectInterviewerItemBinding.inflate(inflater, parent, false))
    }

    inner class ViewHolder(private val binding: SelectInterviewerItemBinding) :
        BaseViewHolder<Selectable<Interviewer>>(binding.root) {

        private lateinit var item: Selectable<Interviewer>

        init {
            binding.root.setOnClickListener {
                listener?.onInterviewerClick(item)
            }
        }

        override fun bind(item: Selectable<Interviewer>) {
            this.item = item
            binding.textViewId.text = String.format("#%s", item.item.id.toString())
            binding.textViewName.text = item.item.name
            binding.buttonTick.visibility = if (item.selected) View.VISIBLE else View.INVISIBLE
        }
    }
}