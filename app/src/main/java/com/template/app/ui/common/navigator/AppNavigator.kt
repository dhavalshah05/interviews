package com.template.app.ui.common.navigator

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.template.app.domain.interviews.models.Interview
import com.template.app.domain.interviews.models.InterviewResult
import com.template.app.ui.addinterview.AddInterviewFragment
import com.template.app.ui.editinterview.EditInterviewFragment
import com.template.app.ui.interviewdetails.InterviewDetailsFragment
import com.template.app.ui.selectdate.SelectDateDialog
import com.template.app.ui.interviewers.InterviewersFragment
import com.template.app.ui.managers.ManagersFragment
import com.template.app.ui.selectinterviewer.SelectInterviewerBottomSheet
import com.template.app.ui.selectmanager.SelectManagerBottomSheet
import com.template.app.ui.selectresult.SelectResultBottomSheet
import com.template.app.ui.settings.SettingsFragment

class AppNavigator(
    fragmentManager: FragmentManager
) : Navigator(fragmentManager) {

    fun navigateToSettingsScreen() {
        fragNavController.pushFragment(SettingsFragment())
    }

    fun navigateToManagersScreen() {
        fragNavController.pushFragment(ManagersFragment())
    }

    fun navigateToInterviewersScreen() {
        fragNavController.pushFragment(InterviewersFragment())
    }

    fun navigateToAddInterviewScreen(requestKeyAddInterview: String) {
        val fragment = AddInterviewFragment()
        fragment.arguments = AddInterviewFragment.createBundle(requestKeyAddInterview)
        fragNavController.pushFragment(fragment)
    }

    fun navigateToEditInterviewScreen(requestKeyEditInterview: String, interview: Interview) {
        val fragment = EditInterviewFragment().apply {
            arguments = EditInterviewFragment.createBundle(requestKeyEditInterview, interview)
        }
        fragNavController.pushFragment(fragment)
    }

    fun navigateToSelectInterviewerScreen(requestKeySelectInterviewer: String, interviewerId: Long?) {
        val dialog = SelectInterviewerBottomSheet.newInstance(requestKeySelectInterviewer, interviewerId)
        dialog.show(fragmentManager, SelectInterviewerBottomSheet.TAG)
    }

    fun navigateToSelectManagerScreen(requestKeySelectManager: String, managerId: Long?) {
        val dialog = SelectManagerBottomSheet.newInstance(requestKeySelectManager, managerId)
        dialog.show(fragmentManager, SelectManagerBottomSheet.TAG)
    }

    fun navigateToSelectDateScreen(requestKeySelectDate: String) {
        val dialog = SelectDateDialog.newInstance(requestKeySelectDate)
        dialog.show(fragmentManager, SelectDateDialog.TAG)
    }

    fun navigateToInterviewDetailsScreen(interview: Interview) {
        val fragment = InterviewDetailsFragment().apply {
            arguments = InterviewDetailsFragment.createBundle(interview)
        }
        fragNavController.pushFragment(fragment)
    }

    fun navigateToSelectResultScreen(requestKeySelectResult: String, interviewResult: InterviewResult) {
        val dialog = SelectResultBottomSheet.newInstance(requestKeySelectResult, interviewResult)
        dialog.show(fragmentManager, SelectResultBottomSheet.TAG)
    }

    /*fun switchToTab() {
        fragNavController.switchTab(FragNavController.TAB1)
    }*/

}