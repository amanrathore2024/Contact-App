package com.example.contact.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contact.data.entities.Contact
import com.example.contacts.data.DAO.ContactDao
import com.example.contacts.data.DAO.DeleteContactDao
import com.example.contacts.data.entities.DeletedContact

@Database(entities = [Contact::class , DeletedContact::class], version = 4 , exportSchema = true,
   autoMigrations = [
      AutoMigration(from = 1 , to = 2),
     AutoMigration(from = 2 , to = 3)
   ])
abstract class ContactDb( ) : RoomDatabase() {

   abstract fun contactDao() : ContactDao
   abstract fun DeleteContactDao() : DeleteContactDao

}