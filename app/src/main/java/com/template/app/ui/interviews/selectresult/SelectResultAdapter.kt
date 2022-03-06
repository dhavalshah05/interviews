package com.template.app.ui.interviews.selectresult

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.template.app.databinding.SelectResultItemBinding
import com.template.app.domain.interviews.models.InterviewResult
import com.template.app.ui.common.models.Selectable
import com.template.app.ui.interviews.models.UiInterviewResult
import com.template.app.util.superadapter.BaseRecyclerViewAdapter
import com.template.app.util.superadapter.viewholder.BaseViewHolder

class SelectResultAdapter :
    BaseRecyclerViewAdapter<Selectable<InterviewResult>, SelectResultAdapter.ViewHolder>() {

    interface Listener {
        fun onResultClick(item: Selectable<InterviewResult>)
    }

    private var listener: Listener? = null

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    override fun getViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): SelectResultAdapter.ViewHolder {
        return ViewHolder(SelectResultItemBinding.inflate(inflater, parent, false))
    }

    inner class ViewHolder(val binding: SelectResultItemBinding) :
        BaseViewHolder<Selectable<InterviewResult>>(binding.root) {

        private lateinit var item: Selectable<InterviewResult>

        init {
            binding.root.setOnClickListener {
                listener?.onResultClick(item)
            }
        }

        override fun bind(item: Selectable<InterviewResult>) {
            this.item = item
            val uiInterviewResult = UiInterviewResult.from(item.item)
            binding.textViewName.text = getString(uiInterviewResult.textResId)
            binding.buttonTick.visibility = if (item.selected) View.VISIBLE else View.INVISIBLE
        }
    }

}