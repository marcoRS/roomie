package com.droidtitan.roomie.add

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import com.droidtitan.roomie.R
import com.droidtitan.roomie.utils.BaseBottomSheet
import com.droidtitan.roomie.utils.hideKeyboard
import com.droidtitan.roomie.words.WordsViewModel
import kotlinx.android.synthetic.main.fragment_add_words.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddWordsFragment : BaseBottomSheet() {

  private val viewModel: WordsViewModel by sharedViewModel()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_add_words, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    buttonSave.setOnClickListener {
      val word = editWord.text.toString()
      when {
        word.isBlank() -> Unit // Update to show a message
        else -> viewModel.insert(word)
      }
      buttonSave.hideKeyboard()
      findNavController().popBackStack()
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
