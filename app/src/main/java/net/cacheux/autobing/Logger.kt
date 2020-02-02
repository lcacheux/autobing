package net.cacheux.autobing

import android.util.Log

const val TAG = "AutoBing"

/**
 * Use inline function and lambda as parameter to properly clean all debug strings in release
 * builds.
 */
inline fun logDebug(message: () -> String) {
    if (BuildConfig.DEBUG) {
        Log.d(TAG, message.invoke())
    }
}

fun logError(message: String, e: Throwable) {
    if (BuildConfig.DEBUG) {
        Log.e(TAG, message, e)
    } else {
        Log.e(TAG, message + ": " + e.javaClass.simpleName)
    }
}