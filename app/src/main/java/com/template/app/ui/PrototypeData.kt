package com.template.app.ui

import com.template.app.domain.interviewers.models.Interviewer
import com.template.app.domain.interviews.models.Interview
import com.template.app.domain.interviews.models.InterviewResult
import com.template.app.domain.managers.models.Manager

object PrototypeData {
    fun getManagers(): List<Manager> {
        val result = mutableListOf<Manager>()
        for (index in 1 until 20) {
            result.add(Manager(id = index.toLong(), name = "Manager $index"))
        }
        return result
    }

    fun getInterviewers(): List<Interviewer> {
        val result = mutableListOf<Interviewer>()
        result.add(Interviewer(id = 1, name = "Kishan Prajapati"))
        result.add(Interviewer(id = 2, name = "Dhaval Shah"))
        result.add(Interviewer(id = 3, name = "Khushbu Thakkar"))
        result.add(Interviewer(id = 4, name = "Jaymin Patel"))
        return result
    }

    fun getInterview(): Interview {
        return Interview(
            id = 1L,
            candidateName = "John Doe",
            experience = "2.5",
            interviewDate = System.currentTimeMillis(),
            result = InterviewResult.REJECTED,
            interviewer = getInterviewers().first(),
            manager = getManagers().first(),
            interviewComments = "Sample comments",
            practicalComments = "",
            practicalLink = ""
        )
    }
}