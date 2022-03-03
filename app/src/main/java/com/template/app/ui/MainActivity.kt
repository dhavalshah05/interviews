package com.template.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.template.app.R
import com.template.app.ui.common.navigator.NavigatorActivity
import com.template.app.ui.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : NavigatorActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getNavigationHostFragmentId(): Int {
        return R.id.mainRoot
    }

    override fun getRootFragments(): List<Fragment> {
        return listOf(LoginFragment())
    }

}