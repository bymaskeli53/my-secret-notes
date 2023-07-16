package com.example.mysecretnotes

import androidx.lifecycle.ViewModel
import com.example.mysecretnotes.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {



    suspend fun insertNoteToRoom(note: Note): Long = repository.insertNoteToRoom(note)

    suspend fun getAllNotesFromRoom(): List<Note> = repository.getAllNotesFromRoom()

    suspend fun getNumberOfNotesFromRoom(): Int = repository.getNumberOfNotesFromRoom()

    suspend fun deleteNoteFromRoomById(id: Int) = repository.deleteNoteFromRoomById(id)

    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("MM.dd.yyyy HH:mm")
        return format.format(date)
    }

    fun currentTimeToLong(): Long {
        return System.currentTimeMillis()
    }

    fun convertDateToLong(date: String): Long {
        val df = SimpleDateFormat("yyyy.MM.dd HH:mm")
        return df.parse(date).time
    }


}