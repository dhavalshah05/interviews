package com.template.app.util.logging

import timber.log.Timber

class ClickableLoggingTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String {
        with(element) {
            return "($fileName:$lineNumber)$methodName()"
        }
    }

}