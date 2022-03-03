package com.template.app.ui.common.navigator

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.template.app.ui.common.activity.ExceptionHandlerActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class NavigatorActivity : ExceptionHandlerActivity() {

    @Inject
    lateinit var navigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.init(getNavigationHostFragmentId(), savedInstanceState, getRootFragments(), provideBottomNavigationVisibilityController())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        navigator.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        if (!navigator.goBack()) {
            super.onBackPressed()
        }
    }

    open fun provideBottomNavigationVisibilityController(): BottomNavigationVisibilityController? {
        return null
    }

    @IdRes
    abstract fun getNavigationHostFragmentId(): Int

    abstract fun getRootFragments(): List<Fragment>
}