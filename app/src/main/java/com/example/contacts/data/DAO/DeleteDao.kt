package com.example.contacts.data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.contact.data.entities.Contact
import com.example.contacts.data.entities.DeletedContact
import kotlinx.coroutines.flow.Flow

@Dao
interface DeleteContactDao {


    @Upsert
    suspend fun  upsertDeletedContact(contactDelete: DeletedContact)
    @Delete
    suspend fun deleteDeleteContact(contactDelete: DeletedContact)

    @Query("SELECT * FROM DELETED_CONTACT")
    fun getDeletedContact(): Flow<List<DeletedContact>>

}