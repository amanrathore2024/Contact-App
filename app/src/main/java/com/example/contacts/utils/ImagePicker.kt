package com.example.contacts.utils

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil3.Uri

@Composable
fun ImagePicker(onImageSelected: (ByteArray) -> Unit) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: android.net.Uri? ->
        uri?.let {
            val inputStream = context.contentResolver.openInputStream(it)
            val byteArray = inputStream?.readBytes()
            inputStream?.close()
            byteArray?.let(onImageSelected)
        }
    }

    Button(onClick = {
        launcher.launch("image/*")
    }) {
        Text("Pick Image")
    }
}
