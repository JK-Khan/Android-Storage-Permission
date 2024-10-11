# StoragePermissionHandler Library

`StoragePermissionHandler` is an Android library that simplifies handling storage permissions across all Android versions (from Android 5.0 to Android 14). It allows developers to request storage access and interact with specific folders, including reading, deleting, copying, and moving files.

## Features

- Request storage permission for reading/writing on all Android versions (5.0 to 14).
- Request access to specific folders.
- Read all files from a selected folder.
- Perform file operations such as copy, delete, and move.
- Compatible with scoped storage introduced in Android 10+.

## Setup

### 1. Add the Library to Your Project
You can include the `StoragePermissionHandler` library in your Android project by downloading the code or adding the `.kt` files directly to your project.

### 2. Usage

To use the `StoragePermissionHandler` library, follow these steps:

#### 1. Initialize the Library
In your `MainActivity`, initialize the `StoragePermissionHandler`:

```kotlin
private lateinit var storagePermissionHandler: StoragePermissionHandler

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    
    storagePermissionHandler = StoragePermissionHandler(this)
}
