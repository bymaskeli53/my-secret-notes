package com.example.mysecretnotes

import androidx.room.*


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note): Long

    @Query("SELECT * FROM Notes")
    suspend fun getAllNotes(): List<Note>


    // If we need to check how many notes do we have in db
    @Query("SELECT COUNT(id) FROM NOTES")
    suspend fun numberOfItemsInDb(): Int

    @Query("DELETE FROM Notes WHERE id = :noteId")
    suspend fun deleteNoteById(noteId: Int)
}