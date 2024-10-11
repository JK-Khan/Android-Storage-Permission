
# StoragePermissionHandler Library

`StoragePermissionHandler` is an Android library that simplifies handling storage permissions across all Android versions (from Android 5.0 to Android 14). It allows developers to request storage access and interact with files, including reading and writing files.

## Features

- Request storage permissions in a simplified manner.
- Handle different permission responses (granted or denied).
- Perform actions based on permission status.
- Compatible with Android 5.0 to Android 14.

## Installation

### Using JitPack

To add `StoragePermissionHandler` to your project using [JitPack](https://jitpack.io/), follow these steps:

1. Add JitPack to your project-level `build.gradle`:

   ```gradle
   allprojects {
       repositories {
           google()
           mavenCentral()
           maven { url 'https://jitpack.io' }
       }
   }
   ```

2. Add the library dependency to your app-level `build.gradle`:

   ```gradle
   dependencies {
       implementation 'com.github.JK-Khan:Android-Storage-Permission:version'
   }
   ```

   Replace `version` with the latest release version (e.g., `v1.0`).

## Usage

Here is a simple example of how to use the `StoragePermissionHandler` in your `MainActivity`:

```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var storagePermissionHandler: StoragePermissionHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        storagePermissionHandler = StoragePermissionHandler(this)

        // Check storage permission
        storagePermissionHandler.checkStoragePermission(
            onPermissionGranted = {
                // Permission is granted
                // Perform tasks that require storage access
            },
            onPermissionDenied = {
                // Permission is denied
                // Show a message or handle accordingly
            }
        )
    }
}
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
