package com.example.contacts.data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.contact.data.entities.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao{
    //crud opertion

    @Upsert
    suspend fun upsert(contact: Contact)

//    @Update
//    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query(" Select * From Contact")
    fun getAll() : Flow<List<Contact>>
}