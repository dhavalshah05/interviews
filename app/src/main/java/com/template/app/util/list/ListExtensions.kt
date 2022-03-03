package com.template.app.util.list

/**
 *
 */
fun <T> List<T>.addItemsAtEnd(items: List<T>): List<T> {
    val newItems = this.toMutableList()
    newItems.addAll(items)
    return newItems.toList()
}

/**
 *
 */
fun <T> List<T>.addItemsAtStart(items: List<T>): List<T> {
    val newItems = this.toMutableList()
    newItems.addAll(0, items)
    return newItems.toList()
}

/**
 *
 */
fun <T> List<T>.addItemAtEnd(item: T): List<T> {
    val newItems = this.toMutableList()
    newItems.add(item)
    return newItems.toList()
}

/**
 *
 */
fun <T> List<T>.addItemAtStart(item: T): List<T> {
    val newItems = this.toMutableList()
    newItems.add(0, item)
    return newItems.toList()
}

/**
 *
 */
fun <T> List<T>.deleteItem(condition: (T) -> Boolean) : List<T> {
    val items = this.toMutableList()
    val index = items.indexOfFirst(condition)
    if (index != -1) {
        items.removeAt(index)
    }
    return items.toList()
}

/**
 *
 */
fun <T> List<T>.deleteItem(index: Int) : List<T> {
    val items = this.toMutableList()
    if (index != -1) {
        items.removeAt(index)
    }
    return items.toList()
}

/**
 *
 */
fun <T> List<T>.updateItem(item: T, condition: (T) -> Boolean) : List<T> {
    val items = this.toMutableList()
    val index = items.indexOfFirst(condition)
    if (index != -1) {
        items[index] = item
    }
    return items.toList()
}