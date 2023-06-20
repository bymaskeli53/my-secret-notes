package com.example.mysecretnotes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note(
    var title: String ,
    var note: String ,
    var date: String ,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)