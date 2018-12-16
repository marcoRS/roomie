package com.droidtitan.wordsample.add

import android.app.Activity
import android.app.Activity.RESULT_CANCELED
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import com.droidtitan.wordsample.R
import kotlinx.android.synthetic.main.fragment_add_words.*

class AddWordsFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_add_words, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    buttonSave.setOnClickListener {
      val word = editWord.text.toString()
      activity?.apply {
        when {
          word.isBlank() -> setResult(RESULT_CANCELED, Intent())
          else -> setResult(Activity.RESULT_OK, Intent().apply { putExtra(EXTRA_REPLY, word) })
        }
        finish()
      }
    }

    editWord.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
      val hasEnterOrGo = keyCode == KeyEvent.KEYCODE_ENTER || keyCode == EditorInfo.IME_ACTION_GO
      return@OnKeyListener when (event.action == KeyEvent.ACTION_DOWN && hasEnterOrGo) {
        true -> buttonSave.callOnClick().let { true }
        false -> false
      }
    })
  }

  companion object {
    const val EXTRA_REPLY = "com.droidtitan.REPLY"
  }
}
