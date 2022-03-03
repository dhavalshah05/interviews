package com.template.app.util.keyboard

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

class KeyboardVisibilityHandler(
    private val activity: Activity
) {

    /**
     *
     */
    fun hideKeyboard() {
        val view = activity.currentFocus
        if (view != null) {
            val inputManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    /**
     *
     */
    fun showKeyboard() {
        val view = activity.currentFocus
        if (view != null) {
            val inputManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

}