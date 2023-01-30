package com.mumayank.airlocation.helpers

import android.content.Context
import com.google.android.gms.common.ConnectionResult.SUCCESS
import com.google.android.gms.common.GoogleApiAvailability

object GooglePlayServicesHelper {

    fun checkIfAvailable(context: Context): Boolean {
        val result = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)
        if (result == SUCCESS) {
            return true
        }
        return false
    }

}