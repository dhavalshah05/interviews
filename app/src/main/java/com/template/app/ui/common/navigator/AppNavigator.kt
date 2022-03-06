package com.template.app.ui.common.navigator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.template.app.R
import com.template.app.domain.interviews.models.Interview
import com.template.app.domain.interviews.models.InterviewResult
import com.template.app.ui.interviews.addinterview.AddInterviewFragment
import com.template.app.ui.interviewers.addinterviewer.AddInterviewerBottomSheet
import com.template.app.ui.managers.addmanager.AddManagerBottomSheet
import com.template.app.ui.interviews.deleteinterview.DeleteInterviewConfirmationDialog
import com.template.app.ui.interviewers.deleteinterviewer.DeleteInterviewerConfirmationDialog
import com.template.app.ui.managers.deletemanager.DeleteManagerConfirmationDialog
import com.template.app.ui.interviews.editinterview.EditInterviewFragment
import com.template.app.ui.interviews.interviewdetails.InterviewDetailsFragment
import com.template.app.ui.selectdate.SelectDateDialog
import com.template.app.ui.interviewers.InterviewersFragment
import com.template.app.ui.managers.ManagersFragment
import com.template.app.ui.interviewers.selectinterviewer.SelectInterviewerBottomSheet
import com.template.app.ui.managers.selectmanager.SelectManagerBottomSheet
import com.template.app.ui.interviews.selectresult.SelectResultBottomSheet
import com.template.app.ui.settings.SettingsFragment

class AppNavigator(
    fragmentManager: FragmentManager
) : Navigator(fragmentManager) {
    
    private fun pushFragment(fragment: Fragment, options: FragNavTransactionOptions? = null) {
        val defaultOptions = options ?: FragNavTransactionOptions.newBuilder().apply {
            customAnimations(R.anim.slide_in, R.anim.fade_out)
        }.build()
        fragNavController.pushFragment(fragment, defaultOptions)        
    }

    fun openSettingsScreen() {
        pushFragment(SettingsFragment())
    }

    fun openManagersScreen() {
        pushFragment(ManagersFragment())
    }

    fun openInterviewersScreen() {
        pushFragment(InterviewersFragment())
    }

    fun openAddInterviewScreen(requestKeyAddInterview: String) {
        val fragment = AddInterviewFragment()
        fragment.arguments = AddInterviewFragment.createBundle(requestKeyAddInterview)
        pushFragment(fragment)
    }

    fun openEditInterviewScreen(requestKeyEditInterview: String, interview: Interview) {
        val fragment = EditInterviewFragment().apply {
            arguments = EditInterviewFragment.createBundle(requestKeyEditInterview, interview)
        }
        pushFragment(fragment)
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
        pushFragment(fragment)
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

    fun openDeleteManagerConfirmationScreen(requestKeyDeleteManagerConfirm: String) {
        val dialog = DeleteManagerConfirmationDialog.newInstance(requestKeyDeleteManagerConfirm)
        dialog.show(fragmentManager, DeleteManagerConfirmationDialog.TAG)
    }

    /*fun switchToTab() {
        fragNavController.switchTab(FragNavController.TAB1)
    }*/

}