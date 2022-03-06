package com.template.app.ui.common.navigator

import androidx.fragment.app.FragmentManager
import com.template.app.domain.interviews.models.Interview
import com.template.app.domain.interviews.models.InterviewResult
import com.template.app.ui.addinterview.AddInterviewFragment
import com.template.app.ui.addinterviewer.AddInterviewerBottomSheet
import com.template.app.ui.addmanager.AddManagerBottomSheet
import com.template.app.ui.deleteinterview.DeleteInterviewConfirmationDialog
import com.template.app.ui.deleteinterviewer.DeleteInterviewerConfirmationDialog
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

    fun openSettingsScreen() {
        fragNavController.pushFragment(SettingsFragment())
    }

    fun openManagersScreen() {
        fragNavController.pushFragment(ManagersFragment())
    }

    fun openInterviewersScreen() {
        fragNavController.pushFragment(InterviewersFragment())
    }

    fun openAddInterviewScreen(requestKeyAddInterview: String) {
        val fragment = AddInterviewFragment()
        fragment.arguments = AddInterviewFragment.createBundle(requestKeyAddInterview)
        fragNavController.pushFragment(fragment)
    }

    fun openEditInterviewScreen(requestKeyEditInterview: String, interview: Interview) {
        val fragment = EditInterviewFragment().apply {
            arguments = EditInterviewFragment.createBundle(requestKeyEditInterview, interview)
        }
        fragNavController.pushFragment(fragment)
    }

    fun openSelectInterviewerScreen(requestKeySelectInterviewer: String, interviewerId: Long?) {
        val dialog = SelectInterviewerBottomSheet.newInstance(requestKeySelectInterviewer, interviewerId)
        dialog.show(fragmentManager, SelectInterviewerBottomSheet.TAG)
    }

    fun openSelectManagerScreen(requestKeySelectManager: String, managerId: Long?) {
        val dialog = SelectManagerBottomSheet.newInstance(requestKeySelectManager, managerId)
        dialog.show(fragmentManager, SelectManagerBottomSheet.TAG)
    }

    fun openSelectDateScreen(requestKeySelectDate: String) {
        val dialog = SelectDateDialog.newInstance(requestKeySelectDate)
        dialog.show(fragmentManager, SelectDateDialog.TAG)
    }

    fun openInterviewDetailsScreen(interview: Interview) {
        val fragment = InterviewDetailsFragment().apply {
            arguments = InterviewDetailsFragment.createBundle(interview)
        }
        fragNavController.pushFragment(fragment)
    }

    fun openSelectResultScreen(requestKeySelectResult: String, interviewResult: InterviewResult) {
        val dialog = SelectResultBottomSheet.newInstance(requestKeySelectResult, interviewResult)
        dialog.show(fragmentManager, SelectResultBottomSheet.TAG)
    }

    fun openDeleteInterviewConfirmationScreen(requestKeyDeleteInterviewConfirm: String) {
        val dialog = DeleteInterviewConfirmationDialog.newInstance(requestKeyDeleteInterviewConfirm)
        dialog.show(fragmentManager, DeleteInterviewConfirmationDialog.TAG)
    }

    fun openAddInterviewerScreen() {
        val dialog = AddInterviewerBottomSheet.newInstance()
        dialog.show(fragmentManager, AddInterviewerBottomSheet.TAG)
    }

    fun openAddManagerScreen() {
        val dialog = AddManagerBottomSheet.newInstance()
        dialog.show(fragmentManager, AddManagerBottomSheet.TAG)
    }

    fun openDeleteInterviewerConfirmationScreen(requestKeyDeleteInterviewerConfirm: String) {
        val dialog = DeleteInterviewerConfirmationDialog.newInstance(requestKeyDeleteInterviewerConfirm)
        dialog.show(fragmentManager, DeleteInterviewerConfirmationDialog.TAG)
    }

    /*fun switchToTab() {
        fragNavController.switchTab(FragNavController.TAB1)
    }*/

}