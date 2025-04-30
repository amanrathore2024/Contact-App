package com.example.contacts.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat

class PhoneCall(
    var context: Context
) {
    fun makeCall(phoneNo : String){
        val intent = Intent (Intent.ACTION_CALL , Uri.parse("tel:$phoneNo") ).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
            == PackageManager.PERMISSION_GRANTED
        ) {
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "CALL_PHONE permission not granted", Toast.LENGTH_SHORT).show()
        }
    }
}