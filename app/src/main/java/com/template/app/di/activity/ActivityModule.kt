package com.template.app.di.activity

import android.app.Activity
import com.template.app.ui.common.navigator.AppNavigator
import com.template.app.ui.common.navigator.NavigatorActivity
import com.template.app.util.keyboard.KeyboardVisibilityHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    /*
    * ****************************************
    * Keyboard
    * ****************************************
    * */

    @Provides
    @ActivityScoped
    fun provideKeyboardVisibilityHandler(activity: Activity): KeyboardVisibilityHandler {
        return KeyboardVisibilityHandler(activity)
    }

    /*
    * ****************************************
    * Navigator
    * ****************************************
    * */

    @Provides
    fun provideNavigatorActivity(activity: Activity): NavigatorActivity {
        return activity as NavigatorActivity
    }

    @Provides
    @ActivityScoped
    fun provideNavigator(activity: NavigatorActivity): AppNavigator {
        return AppNavigator(activity.supportFragmentManager)
    }
}