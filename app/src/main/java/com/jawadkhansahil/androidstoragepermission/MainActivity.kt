package com.jawadkhansahil.androidstoragepermission

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var button: Button
    private lateinit var storagePermissionHandler: StoragePermissionHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        button = findViewById(R.id.button)

        storagePermissionHandler = StoragePermissionHandler(this)

        button.setOnClickListener {

            storagePermissionHandler.checkStoragePermission(
                onDenied = {
                    Toast.makeText(this@MainActivity, "Denied", Toast.LENGTH_SHORT).show()
                },
                onGranted = {
                    Toast.makeText(this@MainActivity, "Granted", Toast.LENGTH_SHORT).show()
                }
            )
        }


    }
}