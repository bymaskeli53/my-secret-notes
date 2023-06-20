package com.example.mysecretnotes

import androidx.room.Query
import com.example.mysecretnotes.Note
import com.example.mysecretnotes.NoteDao
import javax.inject.Inject


/**
 * Repository pattern used for data access point
 * it makes more sense if we also use retrofit to fetch data from remote server
 */
class NoteRepository @Inject constructor(
    private val dao: NoteDao
) {

    suspend fun insertNoteToRoom(note: Note): Long = dao.insertNote(note)

    suspend fun getAllNotesFromRoom() : List<Note> = dao.getAllNotes()

    suspend fun getNumberOfNotesFromRoom(): Int = dao.numberOfItemsInDb()





//            @Insert(onConflict = OnConflictStrategy.REPLACE)
//            suspend fun insertNote(note: Note): Long
//
//    @Query("SELECT * FROM notes")
//    suspend fun getAllNotes(): List<Note>
//
//    @Query("SELECT COUNT(id) FROM notes")
//    suspend fun numberOfNotesInDb(): Int
}