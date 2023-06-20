package com.example.mysecretnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.mysecretnotes.NoteAdapter
import com.example.mysecretnotes.R
import com.example.mysecretnotes.SwipeToDeleteCallback
import com.example.mysecretnotes.databinding.FragmentListNotesBinding
import com.example.mysecretnotes.NoteViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NoteListFragment : Fragment(R.layout.fragment_list_notes) {

    private val viewModel: NoteViewModel by viewModels()

    private val binding get() = _binding!!

    private var _binding: FragmentListNotesBinding? = null




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListNotesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view






    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {

            val noteList = viewModel.getAllNotesFromRoom()
            val adapter = NoteAdapter(noteList)
            binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

            // To divide between items
            binding.recyclerview.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
            binding.recyclerview.adapter = adapter

            val swipeToDeleteCallBack = object : SwipeToDeleteCallback() {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val mutableNoteList = noteList.toMutableList()
                    mutableNoteList.removeAt(position)
                    binding.recyclerview.adapter?.notifyItemRemoved(position)



                }


            }

            val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)

            itemTouchHelper.attachToRecyclerView(binding.recyclerview)



        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}