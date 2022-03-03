package com.template.app.ui.interviewers

import android.view.LayoutInflater
import android.view.ViewGroup
import com.template.app.databinding.InterviewerItemBinding
import com.template.app.domain.interviewers.models.Interviewer
import com.template.app.util.superadapter.BaseRecyclerViewAdapter
import com.template.app.util.superadapter.viewholder.BaseViewHolder

class InterviewersAdapter : BaseRecyclerViewAdapter<Interviewer, InterviewersAdapter.ViewHolder>() {

    interface Listener {
        fun onDeleteInterviewerClick(item: Interviewer)
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
        return ViewHolder(InterviewerItemBinding.inflate(inflater, parent, false))
    }

    inner class ViewHolder(private val binding: InterviewerItemBinding) :
        BaseViewHolder<Interviewer>(binding.root) {

        private lateinit var interviewer: Interviewer

        init {
            binding.buttonDelete.setOnClickListener {
                listener?.onDeleteInterviewerClick(interviewer)
            }
        }

        override fun bind(item: Interviewer) {
            interviewer = item
            binding.textViewId.text = String.format("#%s", item.id.toString())
            binding.textViewName.text = item.name
        }
    }
}