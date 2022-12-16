package com.data.interviewers

object PreviewData {
    fun getInterviewers(): List<Interviewer> {
        val result = mutableListOf<Interviewer>()
        for (index in 1 until 20) {
            result.add(Interviewer(id = index.toLong(), name = "Interviewer $index"))
        }
        /*result.add(Interviewer(id = 1, name = "Kishan Prajapati"))
        result.add(Interviewer(id = 2, name = "Dhaval Shah"))
        result.add(Interviewer(id = 3, name = "Khushbu Thakkar"))
        result.add(Interviewer(id = 4, name = "Jaymin Patel"))*/
        return result
    }
}
