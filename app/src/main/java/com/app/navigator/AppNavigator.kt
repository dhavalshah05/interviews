package com.app.navigator

import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.app.R
import com.feature.interviewers.add.AddInterviewerBottomSheet
import com.feature.interviewers.delete.DeleteInterviewerConfirmationDialog
import com.feature.interviewers.select.SelectInterviewerBottomSheet
import com.feature.interviews.add.AddInterviewFragment
import com.feature.interviews.delete.DeleteInterviewConfirmationDialog
import com.feature.interviews.detail.InterviewDetailsFragment
import com.feature.interviews.detail.selectresult.SelectResultBottomSheet
import com.feature.interviews.edit.EditInterviewFragment
import com.feature.interviews.selectdate.SelectDateDialog
import com.feature.managers.add.AddManagerBottomSheet
import com.feature.managers.delete.DeleteManagerConfirmationDialog
import com.feature.managers.select.SelectManagerBottomSheet
import com.service.navigation.Navigator

class AppNavigator constructor(
    private val fragmentManager: FragmentManager
) : Navigator {

    private val navController: NavController
        get() {
            val navHostFragment = fragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
            return navHostFragment.navController
        }

    private val childFragmentManager: FragmentManager
        get() = fragmentManager.fragments.first().childFragmentManager

    override fun goBack() {
        navController.popBackStack()
    }

    override fun openSplashScreen() {
        navController.navigate(R.id.splashFragment)
    }

    override fun openManagersScreen() {
        navController.navigate(R.id.managersFragment)
    }

    override fun openInterviewersScreen() {
        navController.navigate(R.id.interviewersFragment)
    }

    override fun openDeleteManagerConfirmationScreen(requestKeyDeleteManagerConfirm: String) {
        val dialog = DeleteManagerConfirmationDialog.newInstance(requestKeyDeleteManagerConfirm)
        dialog.show(childFragmentManager, DeleteManagerConfirmationDialog.TAG)
    }

    override fun openAddManagerScreen() {
        val dialog = AddManagerBottomSheet.newInstance()
        dialog.show(childFragmentManager, AddManagerBottomSheet.TAG)
    }

    override fun openDeleteInterviewerConfirmationScreen(requestKeyDeleteInterviewerConfirm: String) {
        val dialog = DeleteInterviewerConfirmationDialog.newInstance(requestKeyDeleteInterviewerConfirm)
        dialog.show(childFragmentManager, DeleteInterviewerConfirmationDialog.TAG)
    }

    override fun openAddInterviewerScreen() {
        val dialog = AddInterviewerBottomSheet.newInstance()
        dialog.show(childFragmentManager, AddManagerBottomSheet.TAG)
    }

    override fun openSettingsScreen() {
        navController.navigate(R.id.settingsFragment)
    }

    override fun openInterviewDetailsScreen(interviewId: Long) {
        val bundle = InterviewDetailsFragment.createBundle(interviewId)
        navController.navigate(R.id.interviewDetailFragment, args = bundle)
    }

    override fun openAddInterviewScreen(requestKeyAddInterview: String) {
        val bundle = AddInterviewFragment.createBundle(requestKeyAddInterview)
        navController.navigate(R.id.addInterviewFragment, args = bundle)
    }

    override fun openEditInterviewScreen(requestKeyEditInterview: String, id: Long) {
        val bundle = EditInterviewFragment.createBundle(requestKeyEditInterview, interviewId = id)
        navController.navigate(R.id.editInterviewFragment, args = bundle)
    }

    override fun openDeleteInterviewConfirmationScreen(requestKeyDeleteInterviewConfirm: String) {
        val dialog = DeleteInterviewConfirmationDialog.newInstance(requestKeyDeleteInterviewConfirm)
        dialog.show(childFragmentManager, DeleteInterviewConfirmationDialog.TAG)
    }

    override fun openSelectResultScreen(requestKeySelectResult: String, result: String) {
        val dialog = SelectResultBottomSheet.newInstance(requestKeySelectResult, result)
        dialog.show(childFragmentManager, SelectResultBottomSheet.TAG)
    }

    override fun openSelectInterviewerScreen(requestKeySelectInterviewer: String, id: Long?) {
        val sheet = SelectInterviewerBottomSheet.newInstance(requestKeySelectInterviewer, id)
        sheet.show(childFragmentManager, SelectInterviewerBottomSheet.TAG)
    }

    override fun openSelectManagerScreen(requestKeySelectManager: String, id: Long?) {
        val sheet = SelectManagerBottomSheet.newInstance(requestKeySelectManager, id)
        sheet.show(childFragmentManager, SelectManagerBottomSheet.TAG)
    }

    override fun openSelectDateScreen(requestKeySelectDate: String) {
        val dialog = SelectDateDialog.newInstance(requestKeySelectDate)
        dialog.show(childFragmentManager, SelectDateDialog.TAG)
    }
}
