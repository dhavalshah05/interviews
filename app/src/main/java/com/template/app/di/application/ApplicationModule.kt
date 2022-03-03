package com.template.app.di.application

import android.content.Context
import com.google.gson.Gson
import com.template.app.data.local.preference.AppPreferences
import com.template.app.data.local.preference.AppSession
import com.template.app.data.local.preference.Session
import com.template.app.eventbus.EventBusPoster
import com.template.app.eventbus.EventBusSubscriber
import com.template.app.util.alert.AlertNotification
import com.template.app.util.imageloader.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.greenrobot.eventbus.EventBus
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    /*
    * ****************************************
    * Gson
    * ****************************************
    * */

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    /*
    * ****************************************
    * Session & Preferences
    * ****************************************
    * */

    @Provides
    @Singleton
    fun provideAppPreferences(@ApplicationContext context: Context): AppPreferences {
        return AppPreferences(context)
    }

    @Provides
    @Singleton
    fun provideSession(appPreferences: AppPreferences, gson: Gson): Session {
        return AppSession(appPreferences, gson)
    }

    /*
    * ****************************************
    * EventBus
    * ****************************************
    * */

    @Provides
    @Singleton
    internal fun provideEventBus(): EventBus {
        return EventBus.getDefault()
    }

    @Provides
    @Singleton
    fun provideEventBusPoster(eventBus: EventBus): EventBusPoster {
        return EventBusPoster(eventBus)
    }

    @Provides
    @Singleton
    internal fun provideEventBusSubscriber(eventBus: EventBus): EventBusSubscriber {
        return EventBusSubscriber(eventBus)
    }

    /*
    * ****************************************
    * ImageLoader
    * ****************************************
    * */

    @Provides
    @Singleton
    fun provideImageLoader(@ApplicationContext context: Context): ImageLoader {
        return ImageLoader(context)
    }

    /*
    * ****************************************
    * Alert Notification
    * ****************************************
    * */

    @Provides
    @Singleton
    fun provideAlertNotification(): AlertNotification {
        return AlertNotification()
    }
}