package com.template.app.data.local.preference

interface Session {

    companion object {
        const val P_IS_LOGGED_IN = "P_IS_LOGGED_IN"
    }

    var pIsLoggedIn: Boolean

    fun clearSession()
}
