package com.example.contacts

import com.example.contacts.data.DAO.ContactDao
import com.example.contact.data.entities.Contact
import com.example.contacts.data.DAO.DeleteContactDao
import com.example.contacts.data.entities.DeletedContact
import kotlinx.coroutines.flow.Flow

class Repository(
    private val contactDao: ContactDao,
    private val deleteContactDao: DeleteContactDao
) {
    suspend fun upsertcon(contact: Contact){
        contactDao.upsert(contact)
    }

    suspend fun deletecon(contact: Contact){
        contactDao.delete(contact)
    }

    fun getAllContact() : Flow<List<Contact>>{
        return contactDao.getAll()
    }

    fun getDeletedContacts(): Flow<List<DeletedContact>> {
        return  deleteContactDao.getDeletedContact()
    }

    suspend fun upsertDeletedContacts(contactDelete: DeletedContact) {
        deleteContactDao.upsertDeletedContact(contactDelete)
    }

    suspend fun deleteDeletedContact(contactDelete: DeletedContact) {
        deleteContactDao.deleteDeleteContact(contactDelete)
    }
}