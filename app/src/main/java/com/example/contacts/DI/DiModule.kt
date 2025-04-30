package com.example.contact.DI

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.contact.data.ContactDb
import com.example.contacts.Repository
import com.example.contacts.data.DAO.DeleteContactDao
import com.example.contacts.data.MIGRATION_3_4
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiModule {

    @Provides
    @Singleton
    fun provideDatabase (application: Application): ContactDb {
        return Room.databaseBuilder(
            application,
            ContactDb::class.java,
            "contact_database"
        ).addMigrations(MIGRATION_3_4).build()
    }

    @Provides
    @Singleton
    fun provideRepository( database : ContactDb): Repository {
            return Repository(database.contactDao() , database.DeleteContactDao())
    }

    @Provides
    @Singleton
    fun provideContext( context: Application): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideDeleteContactDao(database: ContactDb): DeleteContactDao {
        return database.DeleteContactDao()
    }

}