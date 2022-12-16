package com.data.interviews

object PreviewData {

    fun getInterview(): Interview {
        return getInterviews().first()
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
                    interviewerId = 1L,
                    interviewerName = "Some Interviewer",
                    managerId = 2L,
                    managerName = "Some Manager",
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

    private fun getInterviewComments(): String = """
        1. Comment
        2. Comment
        3. Comment
        4. Comment
        5. Comment
    """.trimIndent()
}