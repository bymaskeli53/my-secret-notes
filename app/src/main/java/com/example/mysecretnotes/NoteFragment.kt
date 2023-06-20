package com.example.mysecretnotes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mysecretnotes.Note
import com.example.mysecretnotes.NoteFragmentDirections
import com.example.mysecretnotes.R
import com.example.mysecretnotes.databinding.FragmentNoteBinding
import com.example.mysecretnotes.value
import com.example.mysecretnotes.NoteViewModel
//import com.example.noteapplication.databinding.FragmentNoteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NoteFragment : Fragment(R.layout.fragment_note) {
    private val viewModel: NoteViewModel by viewModels()

    private val binding get() = _binding!!

    private var _binding: FragmentNoteBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSave.setOnClickListener {
            if (binding.edittextNote.value.isNotBlank() && binding.edittextTitle.value.isNotBlank()) {
                val title = binding.edittextTitle.value
                val note = binding.edittextNote.value
                val date = viewModel.convertLongToTime(viewModel.currentTimeToLong())

                val noteObject = Note(title = title,note = note, date = date)

                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.insertNoteToRoom(noteObject)
//                val list = viewModel.getAllNotesFromRoom()
//                println(list)

                    val action = NoteFragmentDirections.actionNoteFragmentToNoteListFragment()
                    findNavController().navigate(action)

                }
            }

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}