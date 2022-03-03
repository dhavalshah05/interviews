package com.template.app.ui.interviews

import android.view.LayoutInflater
import android.view.ViewGroup
import com.template.app.databinding.InterviewCardBinding
import com.template.app.domain.interviews.models.Interview
import com.template.app.util.date.DateUtils
import com.template.app.util.superadapter.BaseRecyclerViewAdapter
import com.template.app.util.superadapter.viewholder.BaseViewHolder
import java.util.*

class InterviewsAdapter : BaseRecyclerViewAdapter<Interview, InterviewsAdapter.ViewHolder>() {

    interface Listener {
        fun onInterviewClick(item: Interview)
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
        return ViewHolder(InterviewCardBinding.inflate(inflater, parent, false))
    }

    inner class ViewHolder(private val binding: InterviewCardBinding) :
        BaseViewHolder<Interview>(binding.root) {

        private lateinit var interview: Interview

        init {
            binding.root.setOnClickListener {
                listener?.onInterviewClick(interview)
            }
        }

        override fun bind(item: Interview) {
            this.interview = item

            binding.textViewId.text = String.format("#%s", item.id.toString())
            binding.textViewName.text = item.candidateName
            binding.interviewerName.text = item.interviewer.name
            binding.textViewExperience.text = item.experience
            binding.textViewResult.text = getString(UiInterviewResult.from(item.result).textResId)

            binding.textViewDate.text = DateUtils.format(
                inputDate = item.interviewDate,
                outFormat = DateUtils.DateFormat.PRIMARY_DATE
            )
        }

    }

}