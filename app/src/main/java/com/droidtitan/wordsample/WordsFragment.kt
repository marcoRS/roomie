package com.droidtitan.wordsample

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.droidtitan.wordsample.add.AddWordsActivity
import com.droidtitan.wordsample.add.AddWordsFragment
import com.droidtitan.wordsample.data.Word
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordsFragment : Fragment() {

  private val viewModel: WordsViewModel by viewModel()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_main, container, false)
  }

  override fun onViewCreated(view: View, savedState: Bundle?) {
    super.onViewCreated(view, savedState)
    val adapter = WordListAdapter(view.context)
    recyclerview.adapter = adapter
    recyclerview.layoutManager = LinearLayoutManager(activity)

    viewModel.allWords.observe(this, Observer<List<Word>> { words ->
      adapter.setWords(words)
    })

    fab.setOnClickListener {
      val intent = Intent(activity, AddWordsActivity::class.java)
      startActivityForResult(intent, WordsFragment.NEW_WORD_REQUEST_CODE)
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == NEW_WORD_REQUEST_CODE && resultCode == RESULT_OK) {
      data?.getStringExtra(AddWordsFragment.EXTRA_REPLY)?.apply { viewModel.insert(this) }
    } else {
      Toast.makeText(context?.applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG)
        .show()
    }
  }

  companion object {
    const val NEW_WORD_REQUEST_CODE = 1
  }
}
