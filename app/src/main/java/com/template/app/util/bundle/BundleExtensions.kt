package com.template.app.util.bundle

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable


/*
* ****************************************
* Common
* ****************************************
* */
// region Common

/**
 * This will return the bundle arguments as Map.
 * This could be helpful when debugging which arguments are being passed in Bundle.
 */
fun Bundle.getContentAsMap(): MutableMap<String, Any?> {
    val resultMap = mutableMapOf<String, Any?>()
    for (key in keySet()) {
        val data = get(key)
        resultMap[key] = data
    }
    return resultMap
}

// endregion Common

/*
* ****************************************
* Parcelable Extensions
* ****************************************
* */
// region Parcelable Extensions

/**
 * This will return the Parcelable for a [key] from Bundle if available.
 * Otherwise, it will return Null.
 */
fun <T: Parcelable> Bundle?.getParcelableValueOrNull(key: String): T? {
    return if (this?.containsKey(key) == true) {
        getParcelable(key) as T?
    } else {
        null
    }
}

/**
 * This will return the Parcelable for a [key] from Bundle if available.
 * Otherwise, it will throw [MissingBundleArgumentException]
 */
fun <T: Parcelable> Bundle?.getParcelableValueOrError(key: String): T {
    if (this == null) {
        throw MissingBundleArgumentException(key)
    } else {
        return if (containsKey(key)) {
            getParcelable(key)!!
        } else {
            throw MissingBundleArgumentException(key)
        }
    }
}

// endregion Parcelable Extensions

/*
* ****************************************
* Serializable Extensions
* ****************************************
* */
// region Serializable Extensions

/**
 * This will return the Serializable for a [key] from Bundle if available.
 * Otherwise, it will return Null.
 */
@Suppress("UNCHECKED_CAST")
fun <T : Serializable> Bundle?.getSerializableValueOrNull(key: String): T? {
    return if (this?.containsKey(key) == true) {
        getSerializable(key) as T?
    } else {
        null
    }
}

/**
 * This will return the Serializable for a [key] from Bundle if available.
 * Otherwise, it will throw [MissingBundleArgumentException]
 */

@Suppress("UNCHECKED_CAST")
fun <T : Serializable> Bundle?.getSerializableValueOrError(key: String): T {
    if (this == null) {
        throw MissingBundleArgumentException(key)
    } else {
        return if (containsKey(key)) {
            getSerializable(key) as T
        } else {
            throw MissingBundleArgumentException(key)
        }
    }
}

// endregion Serializable Extensions


/*
* ****************************************
* Int Extensions
* ****************************************
* */
// region Int Extensions

/**
 * This will return the Int for a [key] from Bundle if available.
 * Otherwise, it will return Null.
 */
fun Bundle?.getIntValueOrNull(key: String): Int? {
    return if (this?.containsKey(key) == true) {
        getInt(key)
    } else {
        null
    }
}

/**
 * This will return the Int for a [key] from Bundle if available.
 * Otherwise, it will throw [MissingBundleArgumentException]
 */
fun Bundle?.getIntValueOrError(key: String): Int {
    if (this == null) {
        throw MissingBundleArgumentException(key)
    } else {
        return if (containsKey(key)) {
            getInt(key)
        } else {
            throw MissingBundleArgumentException(key)
        }
    }
}

// endregion Int Extensions

/*
* ****************************************
* String Extensions
* ****************************************
* */
// region String Extensions

/**
 * This will return the String for a [key] from Bundle if available.
 * Otherwise, it will return Null.
 */
fun Bundle?.getStringValueOrNull(key: String): String? {
    return if (this?.containsKey(key) == true) {
        getString(key)
    } else {
        null
    }
}

/**
 * This will return the String for a [key] from Bundle if available.
 * Otherwise, it will throw [MissingBundleArgumentException]
 */
fun Bundle?.getStringValueOrError(key: String): String {
    if (this == null) {
        throw MissingBundleArgumentException(key)
    } else {
        return if (containsKey(key)) {
            val data = getString(key)
            data ?: throw MissingBundleArgumentException(key)
        } else {
            throw MissingBundleArgumentException(key)
        }
    }
}

// endregion String Extensions

/*
* ****************************************
* Long Extensions
* ****************************************
* */
// region Long Extensions

/**
 * This will return the Long for a [key] from Bundle if available.
 * Otherwise, it will return Null.
 */
fun Bundle?.getLongValueOrNull(key: String): Long? {
    return if (this?.containsKey(key) == true) {
        getLong(key)
    } else {
        null
    }
}

/**
 * This will return the Long for a [key] from Bundle if available.
 * Otherwise, it will throw [MissingBundleArgumentException]
 */
fun Bundle?.getLongValueOrError(key: String): Long {
    if (this == null) {
        throw MissingBundleArgumentException(key)
    } else {
        return if (containsKey(key)) {
            getLong(key)
        } else {
            throw MissingBundleArgumentException(key)
        }
    }
}

// endregion Long Extensions

/*
* ****************************************
* Boolean Extensions
* ****************************************
* */
// region Boolean Extensions

/**
 * This will return the Boolean for a [key] from Bundle if available.
 * Otherwise, it will return Null.
 */
fun Bundle?.getBooleanValueOrNull(key: String): Boolean? {
    return if (this?.containsKey(key) == true) {
        getBoolean(key)
    } else {
        null
    }
}

/**
 * This will return the Boolean for a [key] from Bundle if available.
 * Otherwise, it will throw [MissingBundleArgumentException]
 */
fun Bundle?.getBooleanValueOrError(key: String): Boolean {
    if (this == null) {
        throw MissingBundleArgumentException(key)
    } else {
        return if (containsKey(key)) {
            getBoolean(key)
        } else {
            throw MissingBundleArgumentException(key)
        }
    }
}

// endregion Boolean Extensions

/*
* ****************************************
* Parcelable List Extensions
* ****************************************
* */
// region Parcelable List Extensions

fun <T : Parcelable> Bundle.putList(key: String, value: List<T>) {
    putParcelableArrayList(key, ArrayList(value))
}

/**
 * This will return the Parcelable for a [key] from Bundle if available.
 * Otherwise, it will return Null.
 */
fun <T : Parcelable> Bundle?.getListValueOrNull(key: String): List<T>? {
    return if (this?.containsKey(key) == true) {
        getParcelableArrayList(key)
    } else {
        null
    }
}

/**
 * This will return the Parcelable for a [key] from Bundle if available.
 * Otherwise, it will throw [MissingBundleArgumentException]
 */
@Suppress("RemoveExplicitTypeArguments")
fun <T : Parcelable> Bundle?.getListValueOrError(key: String): List<T> {
    if (this == null) {
        throw MissingBundleArgumentException(key)
    } else {
        return if (containsKey(key)) {
            getParcelableArrayList<T>(key)?.toList() ?: mutableListOf()
        } else {
            throw MissingBundleArgumentException(key)
        }
    }
}

// endregion Parcelable List Extensions