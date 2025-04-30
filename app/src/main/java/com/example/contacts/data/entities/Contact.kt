package com.example.contact.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var Fname : String,
    var Lname : String,
    var PhoneNo : String,
    @ColumnInfo("email" , defaultValue = "")
    var email:String,
    var profile:ByteArray? = null
)
