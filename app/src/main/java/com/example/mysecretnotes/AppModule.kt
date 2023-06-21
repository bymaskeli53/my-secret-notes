package com.example.mysecretnotes

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Dependency injection helps us for creating objects easily
 * For example if we want to create dao object we need to create database object
 * but thanks to dagger hilt library we just create our dependencies in this module
 * When we need a dao object it checks here and saves us from boilerplate code.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context

    ): NoteDatabase =
        Room.databaseBuilder(context , NoteDatabase::class.java , "note_database").build()


    @Singleton
    @Provides
    fun provideDao(db: NoteDatabase): NoteDao = db.getDao()
}