package com.example.mysecretnotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysecretnotes.databinding.ItemNoteBinding

class NoteAdapter(private val noteList: List<Note>) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    inner class NoteViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder , position: Int) {
        holder.binding.textviewNote.text = noteList[position].note
        holder.binding.textviewTitle.text = noteList[position].title
        holder.binding.textviewDate.text = noteList[position].date
    }

    override fun getItemCount() = noteList.size
}