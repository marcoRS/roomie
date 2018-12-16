package com.droidtitan.wordsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivityFragment : Fragment() {

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
  }
}
