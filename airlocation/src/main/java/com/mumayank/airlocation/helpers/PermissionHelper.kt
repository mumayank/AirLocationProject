package com.mumayank.airlocation.helpers

import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

object PermissionHelper {

    fun checkAll(
        activity: ComponentActivity?,
        permissions: List<String>,
        onResult: ((Boolean) -> Unit)?
    ) {
        if (activity == null) {
            onResult?.invoke(false)
            return
        }
        var areAllGranted = true
        permissions.forEach exit@{
            if (
                ContextCompat.checkSelfPermission(activity, it) != PackageManager.PERMISSION_GRANTED
            ) {
                areAllGranted = false
                return@exit
            }
        }
        if (areAllGranted) {
            onResult?.invoke(true)
            return
        }
        val requestPermissionLauncher =
            activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                var areAllGranted = true
                it.forEach exit@{
                    if (it.value.not()) {
                        areAllGranted = false
                        return@exit
                    }
                }
                onResult?.invoke(areAllGranted)
            }
        requestPermissionLauncher.launch(
            permissions.toTypedArray()
        )
    }

}