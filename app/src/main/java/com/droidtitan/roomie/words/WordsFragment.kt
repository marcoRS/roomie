package com.droidtitan.roomie.words

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.droidtitan.roomie.R
import com.droidtitan.roomie.add.AddWordsActivity
import com.droidtitan.roomie.add.AddWordsFragment
import kotlinx.android.synthetic.main.fragment_words.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordsFragment : Fragment() {

  private val viewModel: WordsViewModel by viewModel()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedState: Bundle?
  ): View? {
    setHasOptionsMenu(true)
    return inflater.inflate(R.layout.fragment_words, container, false)
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

    viewModel.allWords.observe(viewLifecycleOwner) { words ->
      adapter.setWords(words)
    }
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