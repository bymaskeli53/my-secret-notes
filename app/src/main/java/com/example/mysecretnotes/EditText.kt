package com.example.mysecretnotes

import android.widget.EditText

fun EditText.value() : String {
     return this.text.trim().toString()
}