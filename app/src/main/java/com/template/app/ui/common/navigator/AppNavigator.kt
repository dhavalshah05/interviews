package com.template.app.ui.common.navigator

import androidx.fragment.app.FragmentManager
import com.template.app.ui.addinterview.AddInterviewFragment
import com.template.app.ui.interviewers.InterviewersFragment
import com.template.app.ui.managers.ManagersFragment
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

    fun navigateToAddInterviewScreen() {
        fragNavController.pushFragment(AddInterviewFragment())
    }

    /*fun switchToTab() {
        fragNavController.switchTab(FragNavController.TAB1)
    }*/

}