package com.template.app.ui.common.navigator

import androidx.fragment.app.FragmentManager
import com.template.app.ui.addinterview.AddInterviewFragment
import com.template.app.ui.selectdate.SelectDateDialog
import com.template.app.ui.interviewers.InterviewersFragment
import com.template.app.ui.managers.ManagersFragment
import com.template.app.ui.selectinterviewer.SelectInterviewerBottomSheet
import com.template.app.ui.selectmanager.SelectManagerBottomSheet
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

    /*fun switchToTab() {
        fragNavController.switchTab(FragNavController.TAB1)
    }*/

}