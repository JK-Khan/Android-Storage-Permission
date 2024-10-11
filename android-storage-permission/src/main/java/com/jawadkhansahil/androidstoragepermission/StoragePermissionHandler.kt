package com.jawadkhansahil.androidstoragepermission

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class StoragePermissionHandler(private val activity: AppCompatActivity) {

    private var onPermissionGranted: (() -> Unit)? = null
    private var onPermissionDenied: (() -> Unit)? = null

    // Define the permission request launcher
    private val requestPermissionLauncher =
        activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                onPermissionGranted?.invoke()  // Perform task if permission granted
            } else {
                onPermissionDenied?.invoke()   // Perform task if permission denied
            }
        }

    // Check if storage permission is granted
    fun checkStoragePermission(
        onGranted: () -> Unit,
        onDenied: () -> Unit
    ) {
        this.onPermissionGranted = onGranted
        this.onPermissionDenied = onDenied

        if (isPermissionGranted()) {
            onGranted()  // Permission is already granted
        } else {
            // Directly request the permission without rationale
            requestPermission()
        }
    }

    // Function to check if permission is granted
    private fun isPermissionGranted(): Boolean {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                // For Android 13+ (API 33+), use READ_MEDIA_IMAGES or similar permissions
                ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.READ_MEDIA_IMAGES
                ) == PackageManager.PERMISSION_GRANTED
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                // For Android 10 to 12, use READ_EXTERNAL_STORAGE permission
                ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            }
            else -> {
                // For Android 9 and below, use both READ and WRITE permissions
                ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            }
        }
    }

    // Function to request the required permission
    private fun requestPermission() {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                // Request READ_MEDIA_IMAGES for Android 13+
                requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                // Request READ_EXTERNAL_STORAGE for Android 10 to 12
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            else -> {
                // Request WRITE_EXTERNAL_STORAGE for Android 9 and below
                requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
    }
}
