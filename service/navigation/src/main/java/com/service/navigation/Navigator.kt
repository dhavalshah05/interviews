package com.service.navigation

interface Navigator {
    fun goBack()
    fun openSplashScreen()
    fun openManagersScreen()
    fun openInterviewersScreen()
    fun openDeleteManagerConfirmationScreen(requestKeyDeleteManagerConfirm: String)
    fun openAddManagerScreen()
    fun openDeleteInterviewerConfirmationScreen(requestKeyDeleteInterviewerConfirm: String)
    fun openAddInterviewerScreen()
    fun openSettingsScreen()
    fun openInterviewDetailsScreen(interviewId: Long)
    fun openAddInterviewScreen(requestKeyAddInterview: String)
    fun openEditInterviewScreen(requestKeyEditInterview: String, id: Long)
    fun openDeleteInterviewConfirmationScreen(requestKeyDeleteInterviewConfirm: String)
    fun openSelectResultScreen(requestKeySelectResult: String, result: String)
    fun openSelectInterviewerScreen(requestKeySelectInterviewer: String, id: Long?)
    fun openSelectManagerScreen(requestKeySelectManager: String, id: Long?)
    fun openSelectDateScreen(requestKeySelectDate: String)
}
