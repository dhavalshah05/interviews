package com.data.managers

object PreviewData {
    fun getManagers(): List<Manager> {
        val result = mutableListOf<Manager>()
        for (index in 1 until 20) {
            result.add(Manager(id = index.toLong(), name = "Manager $index"))
        }
        return result
    }
}
