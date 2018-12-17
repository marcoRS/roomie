package com.droidtitan.roomie.words

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.droidtitan.roomie.R
import com.droidtitan.roomie.data.Word

class WordListAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {

  private val inflater: LayoutInflater = LayoutInflater.from(context)
  private var words: List<Word>? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return object : ViewHolder(inflater.inflate(R.layout.row_word, parent, false)) {}
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    (holder.itemView.findViewById(R.id.textView) as TextView).text = words?.get(position)?.word ?: "No Word"
  }

  fun setWords(words: List<Word>) {
    this.words = words
    notifyDataSetChanged()
  }

  override fun getItemCount() = words?.size ?: 0
}