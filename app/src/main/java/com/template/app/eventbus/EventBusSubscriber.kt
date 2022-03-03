package com.template.app.eventbus

import org.greenrobot.eventbus.EventBus

class EventBusSubscriber(private val eventBus: EventBus) {

    fun register(obj: Any) {
        eventBus.register(obj)
    }

    fun unregister(obj: Any) {
        eventBus.unregister(obj)
    }

}