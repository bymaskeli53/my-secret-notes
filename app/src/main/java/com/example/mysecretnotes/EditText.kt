package com.example.mysecretnotes

import android.widget.EditText

val EditText.value: String get() = text.toString().trim()