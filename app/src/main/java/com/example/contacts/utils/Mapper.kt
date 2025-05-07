package com.example.contacts.utils


import com.example.contact.data.entities.Contact
import com.example.contacts.data.entities.DeletedContact

fun Contact.toDeletedContact(): DeletedContact {
    return DeletedContact(
        id = this.id.toLong(),
        firstName = this.Fname,
        lastName = this.Lname,
        phoneNumber = this.PhoneNo,
        email = this.email
    )
}

fun DeletedContact.toContact(): Contact{
    return Contact(
        id = this.id?.toInt() ?: 0,
        Fname = this.firstName,
        Lname = this.lastName,
        PhoneNo = this.phoneNumber,
        email = this.email,
        profile = null,
    )
}
