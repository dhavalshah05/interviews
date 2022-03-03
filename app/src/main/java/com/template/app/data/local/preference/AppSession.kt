package com.template.app.data.local.preference

import com.google.gson.Gson

class AppSession(
    private val appPreferences: AppPreferences,
    private val gson: Gson = Gson()
) : Session {

    override var pIsLoggedIn: Boolean
        get() = appPreferences.getBoolean(Session.P_IS_LOGGED_IN)
        set(value) {
            appPreferences.putBoolean(Session.P_IS_LOGGED_IN, value)
        }

    override fun clearSession() {
        appPreferences.clearAll()
    }

}
