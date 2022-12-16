package com.service.presentation.dialog

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.service.presentation.R

abstract class FullScreenDialog : DialogFragment() {

    /*
    * ****************************************
    * Lifecycle Functions
    * ****************************************
    * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

}