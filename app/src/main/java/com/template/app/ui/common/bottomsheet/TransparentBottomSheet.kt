package com.template.app.ui.common.bottomsheet

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class TransparentBottomSheet : BottomSheetDialogFragment() {

    /*
    * ****************************************
    * Lifecycle Functions
    * ****************************************
    * */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val parent = view.parent as? View
        parent?.setBackgroundColor(Color.TRANSPARENT)
    }

}