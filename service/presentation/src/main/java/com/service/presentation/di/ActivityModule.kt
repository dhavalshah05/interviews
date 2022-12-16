package com.service.presentation.di

import android.app.Activity
import com.service.presentation.utils.alert.AlertMessage
import com.service.presentation.utils.alert.ToastyAlertMessage
import com.service.presentation.utils.keyboard.KeyboardVisibilityHandler
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
    fun provideKeyboardVisibilityHandler(activity: Activity): KeyboardVisibilityHandler {
        return KeyboardVisibilityHandler(activity)
    }

    @Provides
    @ActivityScoped
    fun provideAlertMessage(activity: Activity): AlertMessage {
        return ToastyAlertMessage(activity)
    }
}
