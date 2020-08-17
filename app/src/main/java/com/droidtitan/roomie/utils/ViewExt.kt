package com.droidtitan.roomie.utils

import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat

fun TextView.showKeyboard() {
  requestFocus()
  getInputMethodManager()?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun TextView.hideKeyboard() {
  clearFocus()
  getInputMethodManager()?.hideSoftInputFromWindow(windowToken, 0)
}

private fun TextView.getInputMethodManager() =
  ContextCompat.getSystemService(context, InputMethodManager::class.java)
