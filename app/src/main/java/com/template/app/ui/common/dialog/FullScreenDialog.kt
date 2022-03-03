package com.template.app.ui.common.dialog

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.template.app.R

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