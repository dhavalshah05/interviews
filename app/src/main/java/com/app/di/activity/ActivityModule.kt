package com.app.di.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.app.navigator.AppNavigator
import com.service.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    @ActivityScoped
    fun provideNavigator(activity: Activity): Navigator {
        val fm = (activity as AppCompatActivity).supportFragmentManager
        return AppNavigator(fm)
    }
}
