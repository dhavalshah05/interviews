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

    fun getInterviews(): List<Interview> {
        val result = mutableListOf<Interview>()
        for (i in 1..10) {
            result.add(
                Interview(
                    id = i.toLong(),
                    candidateName = "John Doe",
                    experience = i.toString(),
                    interviewDate = System.currentTimeMillis(),
                    result = getInterviewResult(i),
                    interviewer = getInterviewers().first(),
                    manager = getManagers().first(),
                    interviewComments = getInterviewComments(),
                    practicalComments = "",
                    practicalLink = ""
                )
            )
        }
        return result
    }

    private fun getInterviewResult(i: Int): InterviewResult {
        return when (i % 5) {
            4 -> InterviewResult.values()[4]
            3 -> InterviewResult.values()[3]
            2 -> InterviewResult.values()[2]
            1 -> InterviewResult.values()[1]
            else -> InterviewResult.values()[0]
        }
    }

    fun getInterview(): Interview {
        return getInterviews().first()
    }

    private fun getInterviewComments(): String = """
        1. Comment
        2. Comment
        3. Comment
        4. Comment
        5. Comment
    """.trimIndent()
}