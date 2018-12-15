package com.droidtitan.wordsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivityFragment : Fragment() {

  private val viewModel: WordsViewModel by viewModel()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_main, container, false)
  }
}
