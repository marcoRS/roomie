package com.droidtitan.wordsample.words

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.droidtitan.wordsample.R
import com.droidtitan.wordsample.add.AddWordsActivity
import com.droidtitan.wordsample.add.AddWordsFragment
import com.droidtitan.wordsample.data.Word
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordsFragment : Fragment() {

  private val viewModel: WordsViewModel by viewModel()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedState: Bundle?): View? {
    setHasOptionsMenu(true)
    return inflater.inflate(R.layout.fragment_main, container, false)
  }

  override fun onViewCreated(view: View, savedState: Bundle?) {
    super.onViewCreated(view, savedState)
    val adapter = WordListAdapter(view.context)

    adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
      override fun onChanged() {
        super.onChanged()
        emptyView.visibility = if (adapter.itemCount == 0) View.VISIBLE else View.INVISIBLE
      }
    })

    recyclerview.adapter = adapter
    recyclerview.layoutManager = LinearLayoutManager(activity)

    viewModel.allWords.observe(this, Observer<List<Word>> { words ->
      adapter.setWords(words)
    })
  }

  fun onFabClick() {
    val intent = Intent(activity, AddWordsActivity::class.java)
    startActivityForResult(intent, NEW_WORD_REQUEST_CODE)
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater.inflate(R.menu.menu_main, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean =
    when (item.itemId) {
      R.id.delete_all -> viewModel.deleteAllWords().let { true }
      else -> super.onOptionsItemSelected(item)
    }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == NEW_WORD_REQUEST_CODE && resultCode == RESULT_OK) {
      data?.getStringExtra(AddWordsFragment.EXTRA_REPLY)?.apply { viewModel.insert(this) }
    }
  }

  companion object {
    const val NEW_WORD_REQUEST_CODE = 1
  }
}