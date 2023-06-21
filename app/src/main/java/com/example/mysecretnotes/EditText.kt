package com.example.mysecretnotes

import android.widget.EditText

/**
 * with this value we save boilerplate code when we need edittext content.
 */

val EditText.value: String get() = text.toString().trim()