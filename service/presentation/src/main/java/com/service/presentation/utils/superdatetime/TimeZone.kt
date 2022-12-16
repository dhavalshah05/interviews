package com.service.presentation.utils.superdatetime

import java.time.ZoneId

interface TimeZone {
    fun getZoneId(): ZoneId

    companion object {
        val DEFAULT = object : TimeZone {
            override fun getZoneId(): ZoneId {
                return ZoneId.systemDefault()
            }
        }
        val UTC = object : TimeZone {
            override fun getZoneId(): ZoneId {
                return ZoneId.of("UTC")
            }
        }
    }
}
