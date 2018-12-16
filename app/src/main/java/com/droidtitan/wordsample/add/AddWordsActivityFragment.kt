package com.droidtitan.wordsample.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.droidtitan.wordsample.R
import android

class AddWordsActivityFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_add_words, container, false)
  }
}
