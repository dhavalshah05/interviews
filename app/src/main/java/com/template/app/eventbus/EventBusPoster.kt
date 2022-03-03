package com.template.app.eventbus

import org.greenrobot.eventbus.EventBus

class EventBusPoster(private val eventBus: EventBus) {

    fun post(event: Any) {
        eventBus.post(event)
    }

}