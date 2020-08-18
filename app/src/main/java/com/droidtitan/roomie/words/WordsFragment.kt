package com.droidtitan.roomie.words

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.droidtitan.roomie.R
import kotlinx.android.synthetic.main.fragment_words.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class WordsFragment : Fragment() {

  private val viewModel: WordsViewModel by sharedViewModel()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_words, container, false)
  }

  override fun onViewCreated(view: View, savedState: Bundle?) {
    super.onViewCreated(view, savedState)

    setupToolbar()

    fab.setOnClickListener { findNavController().navigate(R.id.AddNewWord) }

    val adapter = WordListAdapter(view.context).apply {
      registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
          super.onChanged()
          emptyView.isVisible = this@apply.itemCount == 0
        }
      })
    }

    recyclerview.adapter = adapter
    recyclerview.layoutManager = LinearLayoutManager(activity)

    viewModel.allWords.observe(viewLifecycleOwner) { words ->
      adapter.setWords(words)
    }
  }

  private fun setupToolbar() {
    toolbar.setTitle(R.string.app_name)
    toolbar.inflateMenu(R.menu.menu_home)
    toolbar.setOnMenuItemClickListener { item ->
      when (item.itemId) {
        R.id.delete_all -> viewModel.deleteAllWords().let { true }
        else -> false
      }
    }
  }
}