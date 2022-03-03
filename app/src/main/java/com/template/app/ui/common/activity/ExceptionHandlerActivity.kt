package com.template.app.ui.common.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.template.app.exception.defaultexceptionhandler.ExceptionHandler

abstract class ExceptionHandlerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        ExceptionHandler.register(this)
        super.onCreate(savedInstanceState)
    }

}